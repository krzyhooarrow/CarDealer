from flask import Flask
import mysql.connector
import json

app = Flask(__name__)


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


def selectPredictedPricesById(cursor, id):
    query = """SELECT * FROM predicted_price WHERE offer_id = (%s) """
    cursor.execute(query, (id,))
    return cursor.fetchall()


def cut_first_element_from_tuple(list):
    return [cutFirstElement[1:] for cutFirstElement in map(tuple, list)]


cursor, connection = connect_to_database()


@app.route('/')
def health_check():
    return 'Server online!'


@app.route('/getPrices/<offer_id>')
def getPredictedPrices(offer_id):
    return json.dumps(cut_first_element_from_tuple(selectPredictedPricesById(cursor, offer_id)))


if __name__ == '__main__':
    app.run()
