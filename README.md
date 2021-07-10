# Getting Started

# Introduction

Instructions on how to run equipment-app service.

These instructions should work on Linux and Windows.

## Prerequisites
* IntelliJ Idea
* OpenJDK 8

## Clone application
 git clone https://github.com/vijaimp/equipment-app.git

## Intellij configuration
* import project using option File -> New -> Project from Existing Sources and choose pom.xml file
* add lombok support (https://projectlombok.org/setup/intellij)

## Build and run the app using maven
* mvn package
* java -jar target/equipment-app-0.0.1-SNAPSHOT.jar

### Alternatively, you can run the app without packaging it using
* mvn spring-boot:run

The app will start running at http://localhost:8080

## Explore Rest APIs
The app defines following APIs.

    GET /equipment/search?limit=<Number of equipments to be fetched>
    
    GET /equipment/{equipmentNumber}

    POST /equipment
    example input:
          {
            "address": {
            "streetName": "kone street",
            "city": "espoo",
            "postalCode": "104",
            "country": "Finland"
            },
            "contractStartDate": "2021-07-08",
            "contractEndDate": "2021-07-08",
            "status": "STOPPED"
            }


## User maven tool included in repository
* to run project use `./mvnw clean install` (in linux or MacOs)
* to run project use `./mvnw.cmd clean install` (in windows)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)

