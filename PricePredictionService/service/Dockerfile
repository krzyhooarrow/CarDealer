# set base image (host OS)
FROM python:3.8

# set the working directory in the container
WORKDIR /code

# copy the dependencies file to the working directory
COPY . .

RUN pip install Flask
RUN pip install mysql-connector-python
RUN pip install py_eureka_client
# command to run on container start
CMD [ "python", "./app.py" ]
