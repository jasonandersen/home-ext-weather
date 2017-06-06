#!/bin/bash 
# 
# Start up a local environment running the lastest build of ext-weather and
# an instance of mountebank for service virtualization
# 
# Jason Andersen
# 2016.08.26

# start out in the script's directory
cd "$(dirname "$0")"

# build the ext-weather.jar artifact
echo ""
echo "********** building ext-weather.jar **********"
cd service
mvn clean package -Dmaven.test.skip=true

# build the container
echo ""
echo "********** building ext-weather container **********"
docker build . -t ext-weather:latest

# startup containers required for acceptance tests
echo ""
echo "********** booting up local environment **********"
cd ..
docker-compose -f docker-compose-at.yml up
