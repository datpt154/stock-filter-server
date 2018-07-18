package invalue.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @author Nguye Huy
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
