#!/bin/bash 
# Startup script for the ext-weather service

# Startup filebeat to capture logs
#echo "Starting filebeat"
#filebeat -c ./filebeat.yml &

# Start the self-contained jar running the service under Jetty
echo "Starting ext-weather service"
java -jar ext-weather.jar