package vashaina.ha.weather.ext.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import vashaina.ha.weather.ext.exception.InvalidZipCodeException;

/**
 * Test the validation in the {@link ZipCode} class.
 */
public class ZipCodeTest {

    private ZipCode zip;

    @Test
    public void testHappyPath() {
        zip = new ZipCode("98107");
        assertEquals("98107", zip.toString());
    }

    @Test
    public void testNull() {
        testInvalidConstructorArg(null, "zip code cannot be null");
    }

    @Test
    public void testEmptyString() {
        testInvalidConstructorArg("", "zip code cannot be empty");
    }

    @Test
    public void testAlpha() {
        testInvalidConstructorArg("ABC", "zip code must be numeric");
    }

    @Test
    public void testLessThanFiveCharacters() {
        testInvalidConstructorArg("123", "zip code must be 5 digits");
    }

    private void testInvalidConstructorArg(String arg, String expectedErrorMessage) {
        try {
            zip = new ZipCode(arg);
            fail("no exception was thrown");
        } catch (InvalidZipCodeException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }
}
