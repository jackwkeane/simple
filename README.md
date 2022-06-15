# SimpleApplication
### Created by Jack Keane on 06/14/2022
SimpleApplication is Gradle Project in Kotlin created using Spring Boot 2.7.0.
The application was created to better my understanding of Kotlin, Gradle Projects, Spring Boot, Docker, and working with REST architecture, CRUD repositories, and Apache Kafka.

## Application Qualities
The model being used here is a 'SimpleModel' consistenting of two properties: a UUID key and a String text body.
All entries are stored in a postgres database being hosted by docker.

## Functionality
### POST localhost8080/add
Post mapping that requests a SimpleModel object that will be added to a postgres database hosted by docker.
```
curl --location --request POST 'localhost:8080/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text" : "Hello world!"
}'
```

### POST localhost8080/update
Post mapping that requests a SimpleModel object thats content will be used to update an existing entry in the postgres database.
```
curl --location --request POST 'localhost:8080/update' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id"   : "665ab701-cea8-493c-8a44-884ddf20d6af",
    "text" : "Hello world...updated!"
}'
```

### DELETE localhost8080/delete/{id}
Delete mapping that accepts a String id as a path variable that will used to check if an entry in the postgres database exists with the same UUID and delete it.
```
curl --location --request DELETE 'localhost:8080/delete/665ab701-cea8-493c-8a44-884ddf20d6af'
```

### GET localhost8080/all
Get mapping that returns all of the entries in the postgres database hosted by docker.
```
curl --location --request GET 'localhost:8080/all' 
```

### Apache Kafka Implementation
SimpleApplication implements a Kafka producer named 'SimpleProducer' to publish messages to a Kafka topic named 'simple-model-fact' as well as reads those messages with a Kafka consumer named 'KafkaConsumer'. The application makes use of the Gson API for serializing and deserializeing instances of SimpleModel.

### Runbook
Start postgres by opening Docker and running a new Docker image of PostgreSQL and edit application.proterties to allow for a JDBC database to be locally hosted on the port shown by Docker. To start Kafka messaging, open 
```
~/r15-services-customer/resale
```
and inside call:
```
docker-compose up
```
Use post man to allow for easily making cURL requests to the database. 
