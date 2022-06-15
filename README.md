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

### POST localhost8080/update
Post mapping that requests a SimpleModel object thats content will be used to update an existing entry in the postgres database.

### DELETE localhost8080/delete/{id}
Delete mapping that accepts a String id as a path variable that will used to check if an entry in the postgres database exists with the same UUID and delete it.

### GET localhost8080/all
Get mapping that returns all of the entries in the postgres database hosted by docker.
