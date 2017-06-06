#!/bin/bash 
#
# This script will build any relevant Docker images for this service and register them in the container repo.
# 
# Jason Andersen
# 2016.08.25

# build the ext-weather.jar artifact
echo ""
echo "********** building ext-weather.jar **********"
cd service
mvn clean package -Dmaven.test.skip=true

# build the container
echo ""
echo "********** building ext-weather container **********"
docker build . -t ext-weather:latest
