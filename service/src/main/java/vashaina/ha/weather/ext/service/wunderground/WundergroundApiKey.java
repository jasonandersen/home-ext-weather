package vashaina.ha.weather.ext.service.wunderground;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Retains the API key to use when calling the wunderground API.
 */
@Component
public class WundergroundApiKey {

    private static Logger log = LoggerFactory.getLogger(WundergroundApiKey.class);

    private final String key;

    /**
     * Constructor
     * @param key
     */
    public WundergroundApiKey(@Value("${wunderground.api.key}") String key) {
        log.info("using Wunderground API key: {}", key);
        this.key = key;
    }

    /**
     * Value of the API key
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return key;
    }

}
