package vashaina.ha.weather.ext.correlation;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * Filters all requests and adds a correlation ID to any requests that don't have one. This is
 * copied liberally from a blog post by Larry Reed on using random longs converted to base62
 * as correlation IDs instead of GUIDs.
 * 
 * @author Larry Reed
 * @see http://www.dev-garden.org/2015/11/28/a-recipe-for-microservice-correlation-ids-in-java-servlets/
 */
@Component
public class CorrelationIdFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(CorrelationIdFilter.class);

    private static final String HEADER_CORRELATION = "X-Correlation-Id";
    private static final String KEY_CORRELATION = "correlationId";
    private static final int MAX_ID_SIZE = 50;
    private final Random random = new Random();

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("setting up correlation ID servlet filter");
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try {
            String correlationId = ((HttpServletRequest) request).getHeader(HEADER_CORRELATION);
            correlationId = verifyOrCreateId(correlationId);
            MDC.put(KEY_CORRELATION, correlationId);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(KEY_CORRELATION);
        }
    }

    /**
     * Will either verify the correlation ID or create a new one if one was not passed in
     * @param correlationId
     * @return a valid correlation ID
     */
    private String verifyOrCreateId(String correlationId) {
        String validCorrelationId = correlationId;
        if (validCorrelationId == null) {
            validCorrelationId = generateCorrelationId();
        }
        //prevent on-purpose or accidental DOS attack that
        // fills logs with long correlation id provided by client
        else if (validCorrelationId.length() > MAX_ID_SIZE) {
            log.warn("correlation ID trimmed from {} to {} characters", correlationId.length(), MAX_ID_SIZE);
            validCorrelationId = validCorrelationId.substring(0, MAX_ID_SIZE);
        }
        return validCorrelationId;
    }

    /**
     * @return a random correlation ID from a long integer converted to base62.
     */
    private String generateCorrelationId() {
        long randomNum = random.nextLong();
        String out = Base62Converter.convert(randomNum);
        log.debug("creating a new correlation ID {}", out);
        return out;
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        //noop
    }

}
