package vashaina.ha.weather.ext.correlation;

/**
 * Converts base10 numbers to base62.
 * 
 * @author Larry Reeder
 * @see http://www.dev-garden.org/2015/11/28/a-recipe-for-microservice-correlation-ids-in-java-servlets/
 */
public class Base62Converter {

    private final static String BASE_62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * Converts a long integer to a base62 string
     * @param base10
     * @return a base62 string
     */
    public static String convert(Long input) {

        long base10 = input;
        StringBuilder builder = new StringBuilder();

        //NOTE:  Appending builds a reverse encoded string.  The most significant value
        //is at the end of the string.  You could prepend(insert) but appending
        // is slightly better performance and order doesn't matter here.

        //perform the first selection using unsigned ops to get negative
        //numbers down into positive signed range.
        long index = Long.remainderUnsigned(base10, 62);
        builder.append(BASE_62_CHARS.charAt((int) index));
        base10 = Long.divideUnsigned(base10, 62);
        //now the long is unsigned, can just do regular math ops
        while (base10 > 0) {
            builder.append(BASE_62_CHARS.charAt((int) (base10 % 62)));
            base10 /= 62;
        }
        //
        return builder.reverse().toString();
    }

    /**
     * Private constructor
     */
    private Base62Converter() {
        //no instantiation for you!
    }
}
