package vashaina.ha.weather.ext.test;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

/**
 * Some simple testing utilities that I seem to write every time I build something.
 */
public class TestUtils {

    /**
     * Reads a file from the classpath and loads the contents into a string.
     * @param classPath
     * @return string containing the contents of the file
     * @throws IOException
     */
    public static String readFileFromClasspath(String classPath) throws IOException {
        Resource resource = new ClassPathResource(classPath);
        URI uri = resource.getURI();
        Path path = Paths.get(uri);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    /**
     * Sets up a {@link RestTemplate} to be called one time and return a JSON response. Useful
     * for in-JVM testing of REST service dependencies.
     * @param restTemplate
     * @param response
     */
    public static void mockRestTemplateJsonResponse(RestTemplate restTemplate, String url, String response) {
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
        server.expect(once(), requestTo(url)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));
    }

    /**
     * Retrieves a JSON response from the URL passed in.
     * @param url
     * @param verb
     * @return the response entity from the rest template call
     */
    public static ResponseEntity<String> fetchJsonResponse(String url, HttpMethod verb) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(url, verb, request, String.class);
    }

    /**
     * @param json
     * @return the same JSON string but formatted in pretty print
     */
    public static String prettyPrintJson(String json) {
        throw new UnsupportedOperationException("haven't implemented this yet");
    }

    /**
     * @param json
     * @return the same JSON string but all uneccessary white space removed
     */
    public static String minifyJson(String json) {
        throw new UnsupportedOperationException("haven't implemented this yet");
    }
}
