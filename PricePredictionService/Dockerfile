# set base image (host OS)
FROM python:3.8

# set the working directory in the container
WORKDIR /code

# copy the dependencies file to the working directory
COPY . .

# install dependencies
RUN pip install mysql-connector-python
RUN pip install pandas
RUN pip install pickle5
RUN pip install kafka-python
RUN pip install lightgbm
# copy the content of the local src directory to the working directory
#COPY predictor.py .

# command to run on container start
CMD [ "python", "./predictor.py" ]
