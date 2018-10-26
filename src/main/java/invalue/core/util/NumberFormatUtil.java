package invalue.core.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author Nguyen Phat Huy
 *
 */
public class NumberFormatUtil {
	public static final String DEFAULT_PATERN = "############,##";
	public static Double formatDoubleRound(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
    }
	public static Double formatDouble(double value, int places) {
		String number="";
		switch (places) {
		case 1:
			number=".#";
			break;
		case 2:
			number=".##";
			break;
		case 3:
			number=".###";
			break;
		default:
			break;
		}
		DecimalFormat df2 = new DecimalFormat(number);
		df2.setRoundingMode(RoundingMode.DOWN);
	    return Double.parseDouble(df2.format(value));
    }
}
