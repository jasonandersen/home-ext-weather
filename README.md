# ext-weather
This service calls external weather services to retrieve information about weather forecasts and conditions. Right now the only implemented forecast source is Wunderground.com's public API but there are plans for future implementations of other weather services.

## Compile
Run `mvn clean compile`

## Unit tests
Run `mvn clean test`

## Acceptance tests
To run acceptance tests, run the `run-acceptance-tests.sh` script. This script will:
 * build an artifact based on whatever is in the local repo
 * instantiate a Jetty Docker container
 * start up the web service in the Jetty container
 * start up a Mountebank container used to stub out upstream depencies
 * execute the Cucumber tests (the Cucumber tests will setup and tear down any required test doubles)
 * shutdown the acceptance testing environment

## Local development environment
To start up a local development environment, run the `start-local-environment.sh` script. This script will:
 * build an artifact based on whatever is in the local repo
 * instantiate a Jetty Docker container
 * start up the web service in the Jetty container
 * start up a Mountebank container used to stub out upstream depencies (Mountebank is not currently configured with any test doubles so you'll have to do that manually)
