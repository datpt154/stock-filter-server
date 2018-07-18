package invalue.core.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * @author DuongMinhTu
 * 
 */
public class DateUtil {

	public static final String DATE_FORMAT_STR = "dd/MM/yyyy";

	public static final String DATETIME_FORMAT_STR = "dd/MM/yyyy HH:mm";
	
	public static final String DATE_FORMAT_STR_PRO= "dd/MM/yyyy";
	
	public static final String DATETIME_FORMAT_STR_PRO = "HH:mm dd/MM/yyyy";
	
	public static final String TIME_FORMAT_STR = "HH:mm";

	public static final String ENDTIME = "endTime";

	public static final String BEGINTIME = "beginTime";

	public static final String DEFAULT_DATE = "01/01/1980";
	
	public static final String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss";
	
	public static final String DATE_FORMAT_NOW1 = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_FORMAT_NOW2 = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATE_FORMAT_EXPIRED_DATE = "dd-MM-yyyy";
	
	public static final String DATE_FORMAT_EXPIRED_DATE_EXPORT = "yyyy-MM-dd";
	
	public static final String DATE_DD_MM = "dd/MM";
	public static final String DATE_D_M = "d/M";
	
	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	public static final String DATE_FORMAT_SQL = "dd-MMMM-yyyy";
	
	public static final String DATE_FORMAT_AMS = "yyyyMMdd";
	
	public static final String DATE_FORMAT_FULL = "^([0-9]{2})/([0-9]{2})/([0-9]{4}) (20|21|22|23|[0-1]?\\d{1}):([0-5]?\\d{1})$";

	public static String toYearString(Date date) {
		String dateStr = "";
		try {
			dateStr = new SimpleDateFormat("yyyy").format(date);
		} catch (Exception e) {
//			// LogUtils.logError(e, e.getMessage());
		}
		return dateStr;
	}

	public static String toMonthDayString(Date date) {
		String dateStr = "";
		try {
			dateStr = new SimpleDateFormat("dd/MM").format(date);
		} catch (Exception e) {
//			// LogUtils.logError(e, e.getMessage());
		}
		return dateStr;
	}

//	public static Date getCurrentServerDate() {
//		int timezoneInMin = Configuration.getServerTimezone();
//		Date now = new Date();
//		Calendar calendar = new GregorianCalendar();
//		int offset = calendar.getTimeZone().getOffset(now.getTime());
//		Date serverDate = new Date(now.getTime() - offset + timezoneInMin * 60
//				* 1000L);
//		return serverDate;
//
//	}

	public static Date getCurrentGMTDate() {
		Date now = new Date();
		Calendar calendar = new GregorianCalendar();
		int offset = calendar.getTimeZone().getOffset(now.getTime());
		Date gmtDate = new Date(now.getTime() - offset);
		return gmtDate;
	}

//	public static String getCurrentServerDateString() {
//		int timezoneInMin = Configuration.getServerTimezone();
//		Date now = new Date();
//		Calendar calendar = new GregorianCalendar();
//		int offset = calendar.getTimeZone().getOffset(now.getTime());
//		Date serverDate = new Date(now.getTime() - offset + timezoneInMin * 60
//				* 1000L);
//		return toDateString(serverDate, DATE_FORMAT_STR);
//
//	}

	public static String toDateString(Date date, String format) {
		String dateString = "";
		if (date == null)
			return dateString;
		Object[] params = new Object[] { date };

		try {
			dateString = MessageFormat.format("{0,date," + format + "}", params);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return dateString;
	}

	/**
	 * In case hour section of date object is ending in the form of "00:00", we
	 * don't display it by using Date_format string instead. 17/01/2008 00:00
	 * --> 17/01/2008
	 * 
	 * @param date
	 *            : Date
	 * @return string form of date
	 */
	public static String toDateString(Date date) {
		String dateString = "";
		String format;
		if (date == null)
			return dateString;
		Object[] params = new Object[] { date };
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		if (cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 0) {
			format = DateUtil.DATE_FORMAT_STR;
		} else {
			format = DateUtil.DATETIME_FORMAT_STR_PRO;
		}
		try {
			dateString = MessageFormat
					.format("{0,date," + format + "}", params);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return dateString;
	}

	public static Date toDate(String dateStr, String format) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = (Date) dateFormat.parse(dateStr);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return date;
	}

	public static Date toAnotherDate(String dateStr) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(
					DateUtil.DATETIME_FORMAT_STR);
			date = (Date) dateFormat.parse("31/01/2008 " + dateStr);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return date;
	}

	public static Date toDate(String dateStr, String format, String time) {
		Date date = null;
		Calendar calendar = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = (Date) dateFormat.parse(dateStr);
			calendar = new GregorianCalendar();
			calendar.setTime(date);
			if (ENDTIME.equalsIgnoreCase(time)) {
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);

			} else if (BEGINTIME.equalsIgnoreCase(time)) {
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
			}
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		if (calendar == null) {
			return null;
		}
		return calendar.getTime();
	}

	public static Date toGMTDate(String dateStr, String format, String time,
			int timezoneInMin) {
		Date date = null;
		Calendar calendar = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = (Date) dateFormat.parse(dateStr);
			calendar = new GregorianCalendar();
			calendar.setTime(date);
			if (ENDTIME.equalsIgnoreCase(time)) {
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);

			} else if (BEGINTIME.equalsIgnoreCase(time)) {
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
			}
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		if (calendar == null) {
			return null;
		}
		return convertToGMTDate(calendar.getTime(), timezoneInMin);
	}

	public static Date toGMTDate(String dateStr, int timezoneInMin) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
			date = (Date) dateFormat.parse(dateStr);
			date = convertToGMTDate(date, timezoneInMin);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return date;
	}

	public static Date toGMTDate(String dateStr, int timezoneInMin,
			boolean isFullDate) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
			date = (Date) dateFormat.parse(dateStr);
			if (isFullDate) {
				Date endOfDate = new Date(date.getTime() + 1000 * 60 * 60 * 24
						- 1);
				date = endOfDate;
			}

			date = convertToGMTDate(date, timezoneInMin);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return date;
	}

	public static Date getStartDateOfMonth(String month) {
		Date fromDate = null;
		try {
			Calendar calendar = new GregorianCalendar();
			Date now = new Date();
			calendar.setTime(now);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.MONTH, toCalendarMonth(month));
			fromDate = calendar.getTime();
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return fromDate;
	}

	public static Date getEndDateOfMonth(String month) {
		Date toDate = null;
		try {
			Calendar calendar = new GregorianCalendar();
			Date now = new Date();
			calendar.setTime(now);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.MONTH, toCalendarMonth(month));
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			toDate = calendar.getTime();
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return toDate;
	}

	private static int toCalendarMonth(String month) {
		int monthReturn = Calendar.JANUARY;
		if (month == null) {
			return monthReturn;
		}
		if ("january".equals(month)) {
			monthReturn = Calendar.JANUARY;
		} else if ("february".equals(month)) {
			monthReturn = Calendar.FEBRUARY;
		} else if ("march".equals(month)) {
			monthReturn = Calendar.MARCH;
		} else if ("april".equals(month)) {
			monthReturn = Calendar.APRIL;
		} else if ("may".equals(month)) {
			monthReturn = Calendar.MAY;
		} else if ("june".equals(month)) {
			monthReturn = Calendar.JUNE;
		} else if ("july".equals(month)) {
			monthReturn = Calendar.JULY;
		} else if ("august".equals(month)) {
			monthReturn = Calendar.AUGUST;
		} else if ("september".equals(month)) {
			monthReturn = Calendar.SEPTEMBER;
		} else if ("october".equals(month)) {
			monthReturn = Calendar.OCTOBER;
		} else if ("november".equals(month)) {
			monthReturn = Calendar.NOVEMBER;
		} else if ("december".equals(month)) {
			monthReturn = Calendar.DECEMBER;
		}
		return monthReturn;
	}

	public static Date toGMTDateTime(String dateStr, int timezoneInMin) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT_STR);
			date = (Date) dateFormat.parse(dateStr);
			date = convertToGMTDate(date, timezoneInMin);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			date = null;
		}
		return date;
	}

	public static String toServerDateString(Date date, int timezoneInMin) {
		String dateStr = "";
		try {
			date = convertToServerDate(date, timezoneInMin);
			dateStr = new SimpleDateFormat(DATE_FORMAT_STR).format(date);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
		}
		return dateStr;
	}

	public static String toServerDateTimeString(Date date, int timezoneInMin) {
		String dateStr = "";
		try {
			date = convertToServerDate(date, timezoneInMin);
			dateStr = new SimpleDateFormat(DATETIME_FORMAT_STR).format(date);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			dateStr = "";
		}
		return dateStr;
	}

	public static Date getDateFromDateHour(Date currentDate, int hours,
			int minutes, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(currentDate);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		calendar.add(Calendar.MINUTE, minutes);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	public static Date getDateFromMonths(Date currentDate, int months) {
		// if week = 0, do nothing
		if (months == 0) {
			return currentDate;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();

	}

	public static Date getDateFromDates(Date date, int dayNumber) {

		if (dayNumber == 0) {
			return date;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayNumber);
		return calendar.getTime();
	}

	public static Date getShortDateFromDates(Date date, int dayNumber) {

		if (dayNumber == 0) {
			return date;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayNumber);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static Date getDateFromWeeks(Date currentDate, int weeks) {
		// if week = 0, do nothing
		if (weeks == 0) {
			return currentDate;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(currentDate);
		calendar.add(Calendar.WEEK_OF_YEAR, weeks);
		return calendar.getTime();
	}

	public static Date convertToGMTDate(Date localDate, int timezoneInMin) {
		return convertDate(localDate, -timezoneInMin);
	}

	public static Date convertToServerDate(Date gmtDate, int timezoneInMin) {
		return convertDate(gmtDate, timezoneInMin);
	}

	/**
	 * Return 0 if equals, 1 if date1 > date2, -1 if date1 < date2
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDateWithoutTime(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		if (cal1.equals(cal2)) {
			return 0;
		} else if (cal1.after(cal2)) {
			return 1;
		} else {
			return -1;
		}
	}

	private static Date convertDate(Date srcDate, int timezoneInMin) {
		if (srcDate == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(srcDate);
		calendar.add(Calendar.MINUTE, timezoneInMin);
		Date destDate = calendar.getTime();

		return destDate;
	}

	public static Date now(String formatDate) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		try {
			return sdf.parse(sdf.format(cal.getTime()));
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		try {
			return sdf.parse(sdf.format(cal.getTime()));
		} catch (ParseException e) {
			return new Date();
		}
	}

	
	/**
	 * Gets the month.
	 * 
	 * @param date
	 *            the date
	 * @return the month
	 */
	public static int getMonth(Date date) {
		String month = null;
		DateFormat f = new SimpleDateFormat("MM");
		try {
			month = f.format(date);
			return Integer.parseInt(month);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			return -1;
		}
	}

	/**
	 * Gets the year.
	 * 
	 * @param date
	 *            the date
	 * @return the year
	 */
	public static int getYear(Date date) {
		String year = null;
		DateFormat f = new SimpleDateFormat("yyyy");
		try {
			year = f.format(date);
			return Integer.parseInt(year);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			return -1;
		}
	}

	/**
	 * get second of date
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		String h = null;
		DateFormat f = new SimpleDateFormat("SS");
		try {
			h = f.format(date);
			return Integer.parseInt(h);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			return -1;
		}
	}
	
	/**
	 * Gets the day.
	 * 
	 * @param date
	 *            the date
	 * @return the day
	 */
	public static int getDay(Date date) {
		String day = null;
		DateFormat f = new SimpleDateFormat("dd");
		try {
			day = f.format(date);
			return Integer.parseInt(day);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			return -1;
		}
	}

	/**
	 * Parses the.
	 * 
	 * @param str
	 *            the str
	 * @param format
	 *            the format
	 * @return the date
	 */
	public static Date parse(String str, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf.setLenient(false);
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}


	public static long dateDiff(Date from, Date to) {
	    Calendar cFrom = Calendar.getInstance();
	    Calendar cTo = Calendar.getInstance();
	    cFrom.setTime(from);
	    cTo.setTime(to);
	    long diff = Math.abs(cFrom.getTimeInMillis() - cTo.getTimeInMillis());
	    return diff / (24 * 60 * 60 * 1000);
    }
	public static long dateDiffMiliSecond(Date from, Date to) {
	    Calendar cFrom = Calendar.getInstance();
	    Calendar cTo = Calendar.getInstance();
	    cFrom.setTime(from);
	    cTo.setTime(to);
	    long diff = Math.abs(cFrom.getTimeInMillis() - cTo.getTimeInMillis());
	    return diff;
    }
	
	public static int compareTwoDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		if (cal1.equals(cal2)) {
			return 0;
		} else if (cal1.after(cal2)) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public static Date parse(String str) {
		return parse(str, DATE_FORMAT_NOW);
	}

	public static String format(Date d, String format) {
		if (d == null) {
			return " ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
	
	public static String formatDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_STR);
        if (!format.isEmpty()) {
            df = new SimpleDateFormat(format);
        }
        return df.format(date);
    }
	public static Date addDay(Date date, Integer numDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, numDay);
		Date nextTime = cal.getTime();
		return nextTime;
	}
	
	//shop lite
	public static int dateDiffWithoutTime(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		
		long diff = cal1.getTimeInMillis() - cal2.getTimeInMillis();
		return (int) (diff / (24 * 60 * 60 * 1000));
	}
	public static int compareDateWithoutSecond(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();		
		cal1.setTime(date1);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		Calendar cal2 = Calendar.getInstance();		
		cal2.setTime(date2);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		if (cal1.equals(cal2)) {
			return 0;
		} else if (cal1.after(cal2)) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/**
	 * Gets the hour.
	 * 
	 * @param date
	 *            the date
	 * @return the hour
	 */
	public static int getHour(Date date) {
		String hour = null;
		DateFormat f = new SimpleDateFormat("HH");
		try {
			hour = f.format(date);
			return Integer.parseInt(hour);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			return -1;
		}
	}
	
	/**
	 * Gets the minute.
	 * 
	 * @param date
	 *            the date
	 * @return the minute
	 */
	public static int getMinute(Date date) {
		String minute = null;
		DateFormat f = new SimpleDateFormat("mm");
		try {
			minute = f.format(date);
			return Integer.parseInt(minute);
		} catch (Exception e) {
			// LogUtils.logError(e, e.getMessage());
			return -1;
		}
	}

	/**
	 * @param date
	 * @return
	 * @author tu.duong
	 */
	private static String getLocalizedWeekDay(Date date, Locale currentLocale) {
		SimpleDateFormat dtf = new SimpleDateFormat("EEEE", currentLocale);
		return dtf.format(date);
	}

	public static boolean isThisDateValid(String dateToValidate, String dateFromat){

		if(dateToValidate == null){
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(dateToValidate);
			if(date!=null){
				return true;
			}else{
				return false;
			}
				
		} catch (ParseException e) {
			// LogUtils.logError(e, e.getMessage());
			return false;
		}
	}
	
	public static boolean checkFormatDateDDMMYYYY(String date){
		boolean checkFormat;

		if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
			checkFormat=true;
		}else{
		   checkFormat=false;
		}
		
		return checkFormat;
	}
	public static boolean checkFormatDateDDMMYYYYhhmm(String date){
		boolean checkFormat;

		if (date.matches(DATE_FORMAT_FULL)){
			checkFormat=true;
		}else{
			checkFormat=false;
		}
		
		return checkFormat;
	}
	public static Date parseTimeStampValid(String inputString)
	{ 
		try{
			Date date=new Date(Long.parseLong(inputString));
			if(!"".equals(date.toString())){
				return date;
			}
			else{
				return null;
			}
		}catch (NumberFormatException nfe){
//			LogUtils.logError(nfe, nfe.getMessage());
			return null;
		}
	}
}
