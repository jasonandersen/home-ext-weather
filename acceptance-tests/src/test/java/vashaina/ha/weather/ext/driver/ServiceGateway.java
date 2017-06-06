package vashaina.ha.weather.ext.driver;

import static org.junit.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import vashaina.ha.service.virtual.ServiceDouble;
import vashaina.ha.service.virtual.ServiceDoubleRequest;
import vashaina.ha.service.virtual.ServiceVirtualizer;
import vashaina.ha.weather.ext.driver.wunderground.WundergroundStub;

/**
 * Proxies requests out to the ext-weather service, stubbing out the
 * upstream Wunderground API dependency.
 */
@Component
public class ServiceGateway {

    /*
     * NOTE: this class needs to be thread safe!
     */

    private static Logger log = LoggerFactory.getLogger(ServiceGateway.class);

    private static final int WG_PORT = 7575;
    private static final int RESPONSE_CODE = 200;
    private static final String HTTP_METHOD = "GET";

    @Value("${mountebank.host.name}")
    private String mountebankHost;
    @Value("${wunderground.api.key}")
    private String wundergroundApiKey;
    @Value("${ext.weather.host}")
    private String serviceHost;
    @Value("${ext.weather.port}")
    private String servicePort;
    @Autowired
    private ServiceVirtualizer virtualizer;

    /**
     * Executes a call to the external weather service based on a zip code.
     * @param stub 
     * @param zip 
     * @return the response from the external weather service
     */
    public Response executeFromZip(String zip, WundergroundStub stub) {
        String url = buildServiceUrlFromZip(zip);
        return execute(url, stub);
    }

    /**
     * Executes a call to the external weather service based on a url path.
     * @param path
     * @param stub
     * @return the response from the external weather service
     */
    public Response executeFromPath(String path, WundergroundStub stub) {
        String url = buildServiceUrlFromPath(path);
        return execute(url, stub);
    }

    /**
     * Executes a request.
     * @param url
     * @param stub
     * @return the response from the external weather service
     */
    private Response execute(String url, WundergroundStub stub) {
        try {
            setupServiceDouble(stub);
            log.info("calling external weather service at {}", url);
            ResponseEntity<String> responseEntity = callService(url);
            log.debug("external weather response {}: {}",
                    responseEntity.getStatusCode().toString(), responseEntity.getBody());
            Response response = new Response(
                    responseEntity.getBody(), responseEntity.getStatusCodeValue());
            return response;
        } finally {
            /*
             * make sure we're tearing down our service doubles after execution!
             */
            virtualizer.clearAllDoubles();
        }
    }

    /**
     * @param url
     * @return a response entity from calling the URL
     */
    private ResponseEntity<String> callService(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<>(headers);
        HttpMethod verb = HttpMethod.GET;
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
        try {
            response = restTemplate.exchange(url, verb, request, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        log.info("service call status: {} response: {}", response.getStatusCodeValue(), response.getBody());
        return response;
    }

    /**
     * @param zip
     * @return the URL used to call the system under test
     */
    private String buildServiceUrlFromZip(String zip) {
        return String.format("http://%s:%s/weather/forecast/%s", serviceHost, servicePort, zip);
    }

    /**
     * @param path
     * @return the url used to call the system under test
     */
    private String buildServiceUrlFromPath(String path) {
        return String.format("http://%s:%s%s", serviceHost, servicePort, path);
    }

    /**
     * @param zip
     * @param stub
     * @return a properly built request for a service double
     */
    private void setupServiceDouble(WundergroundStub stub) {
        String path = stub.getPath();
        String response = stub.getResponse();
        ServiceDoubleRequest request = new ServiceDoubleRequest(WG_PORT, path, HTTP_METHOD, RESPONSE_CODE, response);
        ServiceDouble serviceDouble = virtualizer.createDouble(request);
        assertEquals(path, serviceDouble.getPath());
    }

}
