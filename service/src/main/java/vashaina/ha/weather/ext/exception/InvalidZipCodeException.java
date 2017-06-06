package vashaina.ha.weather.ext.exception;

/**
 * Represents an invalid zip code.
 */
public class InvalidZipCodeException extends ExternalWeatherServiceException {

    private final String zipCode;

    /**
     * @param description
     */
    public InvalidZipCodeException(String zipCode, String description) {
        super(description);
        this.zipCode = zipCode == null ? "null" : zipCode;
    }

    /**
     * @return the invalid zip code value, if null will return "null"
     */
    public String getZipCode() {
        return zipCode;
    }

}
