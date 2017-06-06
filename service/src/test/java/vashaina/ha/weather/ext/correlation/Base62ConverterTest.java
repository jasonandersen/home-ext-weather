package vashaina.ha.weather.ext.correlation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing base 62 conversion for correlation.
 */
public class Base62ConverterTest {

    @Test
    public void testBase62Convert() {
        long base10 = 1234567890L;
        String base62 = Base62Converter.convert(base10);
        assertEquals("1LY7VK", base62);
    }

    @Test
    public void testBase62ConvertMaxLong() {
        long base10 = 9223372036854775807L;
        String base62 = Base62Converter.convert(base10);
        assertEquals("AzL8n0Y58m7", base62);
    }
}
