package vashaina.ha.weather.ext.service.wunderground;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Represents the host used to call the Wunderground API.
 */
@Component
public class WundergroundHost {

    private static Logger log = LoggerFactory.getLogger(WundergroundHost.class);
    private static final String PROTOCOL = "http://";
    private static final int DEFAULT_PORT = 80;

    private final String host;
    private final int port;

    /**
     * Constructor 
     * @param host
     */
    public WundergroundHost(String host) {
        this(host, DEFAULT_PORT);
    }

    /**
     * Constructor
     * @param hostName
     * @param hostPort
     */
    @Autowired
    public WundergroundHost(
            @Value("${wunderground.host.name}") String host,
            @Value("${wunderground.host.port}") int port) {
        Validate.notNull(host);
        this.host = trimTrailingSlashFromHost(host);
        this.port = port;
        log.info("using Wunderground host {} on port {}", this.host, this.port);
    }

    /**
     * @param candidate
     * @return a host string with no trailing slash
     */
    protected String trimTrailingSlashFromHost(String candidateHost) {
        if (candidateHost.endsWith("/")) {
            log.warn("Wunderground host configured with trailing slash, should have no trailing slash: {}", candidateHost);
            return candidateHost.substring(0, candidateHost.length() - 1);
        }
        return candidateHost;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String prefix = host.startsWith(PROTOCOL) ? "" : PROTOCOL;
        String suffix = port == DEFAULT_PORT ? "" : ":" + port;
        return prefix + host + suffix;
    }

}
