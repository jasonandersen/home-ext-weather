package vashaina.ha.weather.ext.test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Creates dynamic test strings by replacing variables with test values. For example:
 * 
 * Template text:
 * 
 * Hello, ${NAME}!
 * 
 * Values:
 * "NAME", "World"
 * 
 * will produce:
 * Hello, World!
 */
public class Template {

    private final String templatePath;
    private final Map<String, String> values;

    /**
     * Constructor
     * @param templatePath - classpath path to the template file to open
     * @param values - map of values to replace within the template
     */
    public Template(String templatePath, Map<String, String> values) {
        this.templatePath = templatePath;
        this.values = values;
    }

    /**
     * @return rendered output from the template
     * @throws IOException 
     */
    public String render() throws IOException {
        String output = readFileFromClasspath(templatePath);
        for (Entry<String, String> entry : values.entrySet()) {
            String variable = getVariableRegEx(entry.getKey());
            String replacement = entry.getValue() == null ? entry.getKey() : entry.getValue();
            output = output.replaceAll(variable, replacement);
        }
        return output;
    }

    /**
     * @param key
     * @return a regex that will find the variable within the template
     */
    private String getVariableRegEx(String var) {
        return String.format("\\$\\{%s\\}", var);
    }

    /**
     * Reads a file from the classpath and loads the contents into a string.
     * @param classPath
     * @return string containing the contents of the file
     * @throws IOException
     */
    private String readFileFromClasspath(String classPath) throws IOException {
        Resource resource = new ClassPathResource(classPath);
        URI uri = resource.getURI();
        Path path = Paths.get(uri);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

}
