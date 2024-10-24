
# Multi Profile Preference Based Notification System

## Prerequisite

Java 17  
Docker

## Kafka Topics Details

| Topic | Details | 
|----------|----------|
| user-preference | The user-detection-service (producer) sends messages to this topic with the userId as the key and the message as the value. |
| user-preference-output | The user-preference-stream processes data and stores user-related preferences in this topic. This topic is consumed by both the car-system-service (consumer) and the report-service (consumer).|  


## How to run the project  

## Infrastructure
Kafka is required for messaging functionality, docker compose file contains 1 zookepper, 2 kafka broker and kafka-ui instance. we can start them using following command

`docker compose up -d`

services will be available on localhost on the following ports

| Service | Port |
|----------|----------|
| zookeeper | 22181 |
| kafka-1 | 29092 |
| kafka-2 | 39092 |
| kakfa-ui | 8081 |

## Services

Start user-detection-service,user-preference-stream,car-system-service and report-service with IDE or in terminal using following command in each module directory

`mvn spring-boot:run`

## To stop the project
To stop the running infrastructure, run following command

`docker compose down`
To Stop service kill the process from IDE or terminal.
