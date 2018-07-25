package invalue.core.util;

/**
 * @author Nguyen Phat Huy
 *
 */
public class NumberFormatUtil {
	public static final String DEFAULT_PATERN = "############,##";
	public static Double formatDouble(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
    }
}
