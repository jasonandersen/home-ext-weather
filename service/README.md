This web service is a RESTful web service run under an embedded Jetty server using Spring Boot. To start the application directly:

 * `mvn package`
 * `java -jar target/ext-weather.jar`

To start the service from a Docker container:

 * `mvn package`
 * `docker build . -t ext-weather:latest`
 * `docker run -p "8081:8081" ext-weather:latest`
