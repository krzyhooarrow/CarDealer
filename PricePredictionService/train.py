import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
import lightgbm as lgb
from bayes_opt import BayesianOptimization
from sklearn.metrics import mean_squared_error
import pickle

pd.set_option('display.max_rows', 300)
pd.set_option('display.max_columns', 50)
pd.set_option('display.width', 1000)

LGBM_PARAMETERS = {
    'application': 'regression',
    'metric': 'rmse',
    'boosting': 'gbdt',
    'num_iterations': 200,
}


def load_data():
    names = [
        'audi',
        'bmw',
        'ford',
        'hyundai',
        'merc',
        'opel',
        'skoda',
        'toyota',
        'vw',
    ]

    dfs = [
        pd.read_csv('data/{}.csv'.format(x))
        for x in names
    ]

    for i in range(len(names)):
        dfs[i]['brand'] = names[i]

    df = pd.concat(dfs, axis=0)
    df = df.drop(columns=['tax', 'tax(£)', 'mpg'])

    df_model_unknown = df.copy()
    df_model_unknown['model'] = 'other'

    df_brand_unknown = df_model_unknown.copy()
    df_brand_unknown['brand'] = 'other'

    df = pd.concat([df, df_model_unknown, df_brand_unknown])
    df = df.reset_index(drop=True)

    df['brand'] = df['brand'].apply(lambda x: x.lower().replace(' ', ''))
    df['model'] = df['model'].apply(lambda x: x.lower().replace(' ', ''))
    df['fuelType'] = df['fuelType'].apply(lambda x: x.lower().replace(' ', ''))
    df['transmission'] = df['transmission'].apply(lambda x: x.lower().replace(' ', ''))
    df['car_type'] = df['brand'] + '_' + df['model']
    df['km_run'] = df['mileage'] * 1.609
    df['price_pln'] = df['price'] * 4.93
    df['car_old'] = 2020 - df['year']
    df = df.drop(columns=['mileage', 'price', 'year', 'model', 'brand'])
    df = df.sample(frac=1, random_state=0)
    df = df.reset_index(drop=True)

    car_type_mapping = {
        car_type: idx
        for idx, car_type in enumerate(sorted(list(set(list(df['car_type'].values)))))
    }

    fuel_type_mapping = {
        fuel_type: idx
        for idx, fuel_type in enumerate(sorted(list(set(list(df['fuelType'].values)))))
    }

    transmission_type_mapping = {
        transmission_type: idx
        for idx, transmission_type in enumerate(sorted(list(set(list(df['transmission'].values)))))
    }

    df['car_type'] = df['car_type'].apply(lambda x: car_type_mapping[x])
    df['fuelType'] = df['fuelType'].apply(lambda x: fuel_type_mapping[x])
    df['transmission'] = df['transmission'].apply(lambda x: transmission_type_mapping[x])

    y = df['price_pln'].values

    x_columns = list(df.columns)
    x_columns.remove('price_pln')

    transforming_dict = {
        'car_type_mapping': car_type_mapping,
        'fuel_type_mapping': fuel_type_mapping,
        'transmission_type_mapping': transmission_type_mapping,
        'x_columns': x_columns
    }

    pickle.dump(transforming_dict, open('model/transforming_dict.pkl', "wb"))

    x = df[x_columns].values

    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=0)

    return x_train, y_train, x_test, y_test


def bayes_parameter_opt_lgb(x, y, init_round=15, opt_round=25, n_folds=3, random_seed=0):
    train_data = lgb.Dataset(data=x, label=y, free_raw_data=False)

    def lgb_eval(learning_rate, num_leaves):
        parameters = LGBM_PARAMETERS.copy()
        parameters['learning_rate'] = learning_rate
        parameters['num_leaves'] = int(num_leaves)

        cv_result = lgb.cv(parameters, train_data, nfold=n_folds, seed=random_seed, stratified=False, verbose_eval=0, metrics=['rmse'])

        return -min(cv_result['rmse-mean'])

    lgbBO = BayesianOptimization(
        lgb_eval,
        {
            'learning_rate': (0.1, 0.7),
            'num_leaves': (12, 50),
        },
        random_state=0
    )

    lgbBO.maximize(init_points=init_round, n_iter=opt_round)

    model_rmse = []
    for model in range(len(lgbBO.res)):
        model_rmse.append(lgbBO.res[model]['target'])

    return lgbBO.res[pd.Series(model_rmse).idxmax()]['target'], lgbBO.res[pd.Series(model_rmse).idxmax()]['params']


def train_lgbm():
    x_train, y_train, x_test, y_test = load_data()
    #opt_params = {'learning_rate': 0.3625523267576155, 'num_leaves': 45.887374029719034}
    opt_params = None

    if opt_params is None:
        opt_params = bayes_parameter_opt_lgb(x_train, y_train, init_round=5, opt_round=10, n_folds=3, random_seed=0)[1]

    opt_params['num_leaves'] = int(opt_params['num_leaves'])

    parameters = LGBM_PARAMETERS.copy()
    parameters['learning_rate'] = opt_params['learning_rate']
    parameters['num_leaves'] = opt_params['num_leaves']

    train_data = lgb.Dataset(data=x_train, label=y_train, free_raw_data=False)
    model = lgb.train(parameters, train_set=train_data)
    y_test_pred = model.predict(x_test)
    rmse = np.sqrt(mean_squared_error(y_test, y_test_pred))
    print(rmse)

    pickle.dump(model, open('model/model.pkl', "wb"))


if __name__ == '__main__':
    train_lgbm()
