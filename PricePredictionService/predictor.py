from kafka import KafkaConsumer
import json
import pickle
import pandas as pd
import mysql.connector


def connect_to_database():
    connection = mysql.connector.connect(
        host="localhost",
        user="db_user",
        password="db_user",
        database="car_db"
    )

    cursor = connection.cursor()
    cursor.execute("CREATE TABLE IF NOT EXISTS predicted_price (offer_id bigint, year int(11), price float)")
    connection.commit()
    return cursor, connection


def insertPredictedPrices(cursor, connection, valuesTuple):
    query = """INSERT INTO predicted_price (offer_id, year, price) VALUES (%s, %s, %s) """
    cursor.execute(query, valuesTuple)
    connection.commit()


def selectPredictedPricesById(cursor, id):
    query = """SELECT * FROM predicted_price WHERE offer_id = (%s) """
    cursor.execute(query, id)
    return cursor.fetchall()


def cut_first_element_from_tuple(list):
    return [cutFirstElement[1:] for cutFirstElement in map(tuple, list)]


def start_consuming_messages_from_kafka():
    years_predicting_range = 5

    cursor, connection = connect_to_database()
    transforming_dict = pickle.load(open('model/transforming_dict.pkl', "rb"))
    model = pickle.load(open('model/model.pkl', "rb"))

    consumer = KafkaConsumer('offer-topic', group_id='price-prediction')
    for msg in consumer:
        json_values = json.loads(msg.value)

        if len(selectPredictedPricesById(cursor, (json_values['id'],))) != 0:
            continue

        input = get_input(
            json_values['mark'], json_values['model'], \
            json_values['production_year'], json_values['fuelType'], \
            json_values['mileage'], json_values['capacity'], json_values['gearbox'], transforming_dict)

        inputsList = [input]
        for x in range(1, years_predicting_range):
            input = input.copy()
            input['car_old'] = input['car_old'] + 1
            inputsList.append(input)

        counter = 0
        for x in predict(inputsList, transforming_dict, model):
            insertPredictedPrices(cursor, connection,
                                  (json_values['id'], json_values['production_year'] + counter, x))
            counter += 1


def get_input(make, model, year, fuel_type, mileage, capacity, transmission, transforming_dict):
    make = make.lower()
    model = model.lower().replace(" ", "")
    fuel_type = fuel_type.lower()
    transmission = transmission.lower()

    if make not in [x.split("_")[0] for x in transforming_dict['car_type_mapping'].keys()]:
        make = 'other'
        model = 'other'
    elif model not in [x.split("_")[1] for x in transforming_dict['car_type_mapping'].keys()]:
        model = 'other'

    if fuel_type not in list(transforming_dict['fuel_type_mapping'].keys()):
        fuel_type = 'other'

    if transmission not in list(transforming_dict['transmission_type_mapping'].keys()):
        transmission = 'other'

    return {
        "transmission": transmission,
        "fuelType": fuel_type,
        "engineSize": capacity,
        "car_type": make + "_" + model,
        "km_run": mileage,
        "car_old": 2020 - year
    }


def predict(input, transforming_dict, model):
    df = pd.DataFrame.from_records(input)
    df['car_type'] = df['car_type'].apply(lambda x: transforming_dict['car_type_mapping'][x])
    df['fuelType'] = df['fuelType'].apply(lambda x: transforming_dict['fuel_type_mapping'][x])
    df['transmission'] = df['transmission'].apply(lambda x: transforming_dict['transmission_type_mapping'][x])
    x = df[transforming_dict['x_columns']]
    return model.predict(x)


if __name__ == '__main__':
    start_consuming_messages_from_kafka()

