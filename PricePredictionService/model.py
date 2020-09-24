from os import listdir
from os.path import isfile, join
import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.ensemble import RandomForestRegressor

path="data/"
frames = []
files = [f for f in listdir(path) if isfile(join(path, f))]
for f in files:
    frame = pd.read_csv(path + f)
    frames.append(frame)

dataframe = pd.concat(frames, sort=False)
dataframe.drop(['tax'], axis=1)
df_cat = dataframe[['transmission','fuelType']]
df_cat = pd.get_dummies(df_cat)
scaler = StandardScaler()
df_num = dataframe[['mileage','mpg','engineSize']]
scaler.fit_transform(df_num[['mileage','mpg','engineSize']])
df1 = pd.concat([df_num,df_cat,dataframe['price']],axis=1)
X = df1.drop("price", axis = 1)
y = df1["price"]
X_train,  X_test, y_train, y_test = train_test_split(X, y, test_size=0.30, random_state=101)
forest = RandomForestRegressor()
forest.fit(X_train,y_train)
forest_y_pred = forest.predict(X_test)
forest_rmse = np.sqrt(mean_squared_error(y_test,forest_y_pred))
forest_r2score = r2_score(y_test,forest_y_pred)

print("R2 score is ", forest_r2score)
print("rmse is ", forest_rmse)
