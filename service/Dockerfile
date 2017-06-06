# Container to run the ext-weather service.

FROM oracle-jdk8

ENV EXTWEATHER_HOME /opt/ext-weather
ARG user=service

# Install Filebeat to ship logfiles to logstash
RUN apt-get update && \
    apt-get install curl && \
    curl -L -O https://download.elastic.co/beats/filebeat/filebeat_1.3.0_amd64.deb && \
    dpkg -i filebeat_1.3.0_amd64.deb && \
    rm -rf /var/lib/apt/lists/* 

# Create the service group and user so we don't have to run it under root
RUN groupadd -g 1000 service && \
    useradd -d "$EXTWEATHER_HOME" -u 1000 -g 1000 -m -s /bin/bash ${user}

# Setup start script
COPY start.sh ${EXTWEATHER_HOME}
RUN chmod a+x ${EXTWEATHER_HOME}/start.sh && \
    chown service:service ${EXTWEATHER_HOME}/start.sh

# Run as service user 
USER ${user}
COPY target/ext-weather.jar ${EXTWEATHER_HOME}
COPY filebeat.yml ${EXTWEATHER_HOME}
WORKDIR ${EXTWEATHER_HOME}
ENTRYPOINT ${EXTWEATHER_HOME}/start.sh