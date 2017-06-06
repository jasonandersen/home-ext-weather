package vashaina.ha.weather.ext.domain;

import org.apache.commons.lang.math.NumberUtils;

import vashaina.ha.weather.ext.exception.InvalidZipCodeException;

/**
 * Represents a USPS zip code.
 */
public class ZipCode {

    private final String zip;

    /**
     * Constructor
     * @param string
     * @throws IllegalArgumentException when zip code is not valid
     */
    public ZipCode(String zip) {
        validate(zip);
        this.zip = zip;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return zip;
    }

    /**
     * Validates a candidate zip code.
     * @param candidate
     */
    private void validate(String candidate) {
        if (candidate == null) {
            throw new InvalidZipCodeException(candidate, "zip code cannot be null");
        }
        if (candidate.isEmpty()) {
            throw new InvalidZipCodeException(candidate, "zip code cannot be empty");
        }
        if (!NumberUtils.isNumber(candidate)) {
            throw new InvalidZipCodeException(candidate, "zip code must be numeric");
        }
        if (candidate.length() != 5) {
            throw new InvalidZipCodeException(candidate, "zip code must be 5 digits");
        }

    }

}
