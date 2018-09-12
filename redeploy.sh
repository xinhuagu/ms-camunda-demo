#!/bin/bash
mvn -f ./webrest/pom.xml clean package; docker-compose build camunda; docker-compose up -d camunda