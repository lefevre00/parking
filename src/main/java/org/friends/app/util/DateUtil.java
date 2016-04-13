package org.friends.app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {

	private static final String SHORT_PATTERN_EN = "yyyy-MM-dd";
	private static final String SHORT_PATTERN_FR = "dd/MM/yyyy";
	private static final String FULL_PATTERN = "EEEE dd/MM/yyyy";

	/**
	 * @param date The date to format
	 * @return A date given following {@link #SHORT_PATTERN_EN} pattern  
	 */
	public static String dateToString(LocalDate date, Locale locale) {
		return getShortFormatter(locale).format(date);
	}
	
	public static String dateToString(LocalDate date) {
		return dateToString(date, null);
	}

	public static String dateToFullString(LocalDate date) {
		return getFullFormatter().format(date);
	}

	public static LocalDate stringToDate(String maDate) {
		return stringToDate(maDate, null);
	}
	
	public static LocalDate stringToDate(String maDate, Locale locale) {
		return LocalDate.parse(maDate, getShortFormatter(locale));
	}

	private static DateTimeFormatter getShortFormatter(Locale locale) {
		String pattern = SHORT_PATTERN_EN;
		if (Locale.FRANCE.equals(locale))
			pattern = SHORT_PATTERN_FR;
		return DateTimeFormatter.ofPattern(pattern);
	}
	
	private static DateTimeFormatter getFullFormatter() {
		return DateTimeFormatter.ofPattern(FULL_PATTERN);
	}
}