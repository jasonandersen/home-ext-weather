package vashaina.ha.weather.ext.exception;

/**
 * Thrown when a call to an external service fails.
 */
public class ExternalCallFailedException extends ExternalWeatherServiceException {

    /**
     * Constructor
     * @param description
     */
    public ExternalCallFailedException(String description) {
        super(description);
    }

    /**
     * Constructor with causal exception
     * @param description
     * @param e
     */
    public ExternalCallFailedException(String description, Throwable e) {
        super(description, e);
    }

}
