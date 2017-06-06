#!/bin/bash 
# 
# This script will build a container from the latest ext-weather code,
# run that container and a service virtualization container to stub out
# any downstream services. Then it will run all the Cucumber tests
# against the service.
# 
# Jason Andersen
# 2016.08.25

# start out in the script's directory
cd "$(dirname "$0")"

# Compile source and create Docker container
./build-artifacts.sh

# startup containers required for acceptance tests
echo ""
echo "********** booting up acceptance testing environment **********"
docker-compose -f docker-compose-at.yml up -d

# wait for containers to start up
# pretty sure there's a better way to do this
sleep 10s

# execute acceptance tests
echo ""
echo "********** running acceptance tests **********"
cd acceptance-tests
mvn clean test 

# shutdown the containers
echo ""
echo "********** shutting down environment **********"
cd ..
docker-compose -f docker-compose-at.yml stop
