#!groovy
/*
 * Groovy script to construct a build pipeline for the ext-weather service.
 */
node {

	def projectId = 'ext-weather'
	def projectRoot = "${projectId}/service"
	def mvnHome = tool 'Maven_3.3.9' //<-- global tool name configured in Jenkins
	def ver = version(projectRoot)

	// grab all the code and figure out the version we're operating with
    stage 'Checkout'
    git '/opt/code/home-automation/'
    if (ver) {
    	echo "Building version ${projectId} ${ver}"
    }

    // compile the code
    stage 'Build'
    sh "${mvnHome}/bin/mvn -f ${projectRoot}/pom.xml clean compile"
   
    // run the unit tests and archive the results
    stage 'Unit Test'
    sh "${mvnHome}/bin/mvn -f ${projectRoot}/pom.xml test"
    step([$class: 'JUnitResultArchiver', testResults: "${projectRoot}/target/surefire-reports/*.xml"])

    // have SonarQube run static analysis
    stage 'Static Analysis'
    sh "${mvnHome}/bin/mvn -f ${projectRoot}/pom.xml sonar:sonar " +
       		"-Dsonar.projectKey=ext-weather " +
       		"-Dsonar.projectName=ExternalWeatherServices " +
       		"-Dsonar.projectVersion=${ver} " +
       		"-Dsonar.sources=src/main/java " +
       		"-Dsonar.host.url=http://sonarqube:9000"

	/*
	 * TODO
	 *
	 * stage 'Publish Artifacts'
	 *		- tag repo with ${projectId}:${ver}:latest
	 *		- publish ext-weather.jar in Jenkins
	 * 		- publish docker container with ext-weather:latest
	 *
	 * stage 'Acceptance Test'
	 * 		- run docker-compose-at.yml to start environment
	 *			- grab ext-weather:latest container
	 * 		- run BDD tests
	 *		- archive BDD results
	 *		- tear down docker environment
	 *		- on success, tag repo with ${projectId}:${ver}:latest-acceptance-tested
	 *
	 * stage 'Performance Test'
	 */
}

/*
 * Grab the version number out of the pom.xml file.
 */
def version(projectRoot) {
	def matcher = readFile("${projectRoot}/pom.xml") =~ '<version>(.+)</version>'
	matcher ? matcher[0][1] : null
}
