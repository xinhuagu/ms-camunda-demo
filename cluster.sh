#!/bin/bash

docker service create --name db  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=process-engine --network mydrupal  mysql:5
docker service create --name camunda  --replicas 3 -p 8080:8080 -e DB_DRIVER=com.mysql.jdbc.Driver -e DB_URL=jdbc:mysql://db:3306/process-engine?autoReconnect=true -e DB_USERNAME=root -e DB_PASSWORD=root -e WAIT_FOR=db:3306 --network mydrupal camunda/camunda-bpm-platform:latest
