package vashaina.ha.weather.ext.exception;

/**
 * Base exception class to extend from for any specific ext-weather 
 * service exceptions.
 */
public class ExternalWeatherServiceException extends RuntimeException {

    /**
     * Constructor
     * @param description
     */
    public ExternalWeatherServiceException(String description) {
        super(description);
    }

    /**
     * Constructor specifying a causal exception
     * @param description
     * @param causalException
     */
    public ExternalWeatherServiceException(String description, Throwable causalException) {
        super(description, causalException);
    }
}
