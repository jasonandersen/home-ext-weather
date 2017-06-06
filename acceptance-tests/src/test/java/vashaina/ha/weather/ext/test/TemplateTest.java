package vashaina.ha.weather.ext.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Just some quick tests to see if we did the {@link Template} class right.
 */
public class TemplateTest {

    private Template template;

    @Before
    public void setupTemplate() {
        Map<String, String> values = new HashMap<>();
        values.put("REPLACE", "[replaced text]");
        template = new Template("data/simple-template.txt", values);
    }

    @Test
    public void testTemplate() throws IOException {
        String output = template.render();
        assertEquals("This template is just used to test the templating engine. [replaced text] <-- This should get replaced.",
                output);
    }
}
