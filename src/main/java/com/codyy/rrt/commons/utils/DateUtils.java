package com.codyy.rrt.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

	public static String PATTERN_DATE = "yyyy-MM-dd";
	public static String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static String defaultPattern = PATTERN_DATETIME;
	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", java.util.Locale.US);

	public static String format(Date date) {
		return format(date, defaultPattern);
	}

	public static String format(long millis) {
		return format(millis, defaultPattern);
	}

	public static String format(Calendar calendar) {
		return format(calendar, defaultPattern);
	}

	public static String format(Date date, String pattern) {
		if (date == null)
			return null;
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		String result = formater.format(date);
		return result;
	}

	private static Date tempDate = new Date();

	public static String format(long millis, String pattern) {
		tempDate.setTime(millis);
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		String result = formater.format(tempDate);
		return result;
	}

	public static String currentTimeStamp() {
		return Integer.toString(new Long(System.currentTimeMillis() / 1000)
				.intValue());
	}

	public static String format(Calendar calendar, String pattern) {
		if (calendar == null)
			return null;
		Date date = calendar.getTime();
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		String result = formater.format(date);
		return result;
	}

	public static Date parseDate(String strDate) {
		if (strDate == null || strDate.length() == 0)
			return null;
		try {
			Date date = parseDate(strDate, defaultPattern);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Date parseDate(String strDate, String pattern)
			throws ParseException {
		if (strDate == null || strDate.length() == 0)
			return null;
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		Date result = formater.parse(strDate);
		return result;
	}

	/*-----------------下面所有的compare方法返回1, 0, -1 三种值-----------------*/
	// 比较年份
	public static int compareYear(Date dateOne, Date dateTwo) {
		return compareField(dateOne, dateTwo, Calendar.YEAR);
	}

	// 比较从year到month字段
	public static int compareYearToMonth(Date dateOne, Date dateTwo) {
		int result = compareField(dateOne, dateTwo, Calendar.YEAR);
		if (result != 0)
			return result;
		else
			return compareField(dateOne, dateTwo, Calendar.MONTH);
	}

	// 比较从year到day字段
	public static int compareYearToDay(Date dateOne, Date dateTwo) {
		int result = compareField(dateOne, dateTwo, Calendar.YEAR);
		if (result != 0)
			return result;
		else
			return compareField(dateOne, dateTwo, Calendar.DAY_OF_YEAR);
	}

	// 比较从year到hour字段
	public static int compareYearToHour(Date dateOne, Date dateTwo) {
		int result = compareField(dateOne, dateTwo, Calendar.YEAR);
		if (result != 0)
			return result;
		else {
			result = compareField(dateOne, dateTwo, Calendar.DAY_OF_YEAR);
			if (result != 0)
				return result;
			else {
				return compareField(dateOne, dateTwo, Calendar.HOUR_OF_DAY);
			}
		}
	}

	// 比较从year到minutes字段
	public static int compareYearToMinutes(Date dateOne, Date dateTwo) {
		int result = compareField(dateOne, dateTwo, Calendar.YEAR);
		if (result != 0)
			return result;
		result = compareField(dateOne, dateTwo, Calendar.DAY_OF_YEAR);
		if (result != 0)
			return result;
		result = compareField(dateOne, dateTwo, Calendar.HOUR_OF_DAY);
		if (result != 0)
			return result;
		return compareField(dateOne, dateTwo, Calendar.MINUTE);
	}

	// 比较从year到second字段
	public static int compareYearToSeconds(Date dateOne, Date dateTwo) {
		int result = compareField(dateOne, dateTwo, Calendar.YEAR);
		if (result != 0)
			return result;
		result = compareField(dateOne, dateTwo, Calendar.DAY_OF_YEAR);
		if (result != 0)
			return result;
		result = compareField(dateOne, dateTwo, Calendar.HOUR_OF_DAY);
		if (result != 0)
			return result;
		result = compareField(dateOne, dateTwo, Calendar.MINUTE);
		if (result != 0)
			return result;
		return compareField(dateOne, dateTwo, Calendar.SECOND);
	}

	// 比较两个日期是否完全相同
	public static int compareYearToMillisecond(Date dateOne, Date dateTwo) {
		return compareDate(dateOne, dateTwo);
	}

	// 比较两个日期是否完全相同
	public static int compareDate(Date dateOne, Date dateTwo) {
		if (dateOne == null)
			throw new IllegalArgumentException("The dateOne must not be null");
		if (dateTwo == null)
			throw new IllegalArgumentException("The dateTwo must not be null");
		long result = dateOne.getTime() - dateTwo.getTime();
		if (result > 0)
			return 1;
		if (result < 0)
			return -1;
		return 0;
	}

	public static int compareMonth(Date dateOne, Date dateTwo) {
		return compareField(dateOne, dateTwo, Calendar.MONTH);
	}

	public static int compareWeekOfYear(Date dateOne, Date dateTwo) {
		return compareField(dateOne, dateTwo, Calendar.WEEK_OF_YEAR);
	}

	public static int compareWeekOfMonth(Date dateOne, Date dateTwo) {
		return compareField(dateOne, dateTwo, Calendar.WEEK_OF_MONTH);
	}

	public static int compareDayOfYear(Date dateOne, Date dateTwo) {
		return compareField(dateOne, dateTwo, Calendar.DAY_OF_YEAR);
	}

	public static int compareDayOfMonth(Date dateOne, Date dateTwo) {
		return compareField(dateOne, dateTwo, Calendar.DAY_OF_MONTH);
	}

	public static int compareDayOfWeek(Date dateOne, Date dateTwo) {
		return compareField(dateOne, dateTwo, Calendar.DAY_OF_WEEK);
	}

	private static Calendar compareCalendarOne = Calendar.getInstance();
	private static Calendar compareCalendarTwo = Calendar.getInstance();

	private static int compareField(Date dateOne, Date dateTwo,
			int calendarField) {
		if (dateOne == null)
			throw new IllegalArgumentException("The dateOne must not be null");
		if (dateTwo == null)
			throw new IllegalArgumentException("The dateTwo must not be null");
		compareCalendarOne.setTime(dateOne);
		compareCalendarTwo.setTime(dateTwo);
		int result = compareCalendarOne.get(calendarField)
				- compareCalendarTwo.get(calendarField);
		if (result > 0)
			return 1;
		if (result < 0)
			return -1;
		return 0;
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of years to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of months to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of weeks to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of days to a date returning a new object. The original date
	 * object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of hours to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of minutes to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of seconds to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of milliseconds to a date returning a new object. The
	 * original date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

	private static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	/**
	 * 把yyyy-MM-dd hh:mm:ss格式的日期字符串转换成 sql.Timestamp 类型 by wx
	 * 
	 * @param str
	 * @return
	 */
	public static java.sql.Timestamp parseTimestampString(String str) {
		java.sql.Timestamp timestamp = null;
		if (str != null && !str.equals("") && !str.equals(" ")) {
			try {
				timestamp = new java.sql.Timestamp(datetimeFormat.parse(str)
						.getTime());
			} catch (ParseException e) {
			}
		}
		return timestamp;
	}

	/**
	 * 字符串转时间戳
	 * 
	 * @param strDate
	 *            时间
	 * @param Format
	 *            格式 如 yyyy-MM-dd HH:mm:ss
	 * @return second 时间戳 秒
	 */
	public static int strToNum(String strDate, String Format) {

		SimpleDateFormat datetimeFormat = new SimpleDateFormat(Format,
				java.util.Locale.US);

		Date strToDate = null;
		try {
			strToDate = datetimeFormat.parse(strDate);
		} catch (ParseException e) {
		}

		long timeStemp = strToDate.getTime();

		int second = (int) (timeStemp / 1000);

		return second;
	}

	/*
	 * 获取日期所在季度的开始时间
	 */
	public static String getQuarterStartTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int currentMonth = Integer.parseInt(StringUtils.substringBetween(date,
				"-"));
		System.out.println(currentMonth);
		String now = null;
		try {
			Date dateTime = sdf.parse(date);
			c.setTime(dateTime);
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 2);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 6);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = sdf.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return now;
	}

	/**
	 * 当获取日期所在季度的结束时间
	 */
	public static String getQuarterEndTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int currentMonth = Integer.parseInt(StringUtils.substringBetween(date,
				"-"));
		String now = null;
		try {
			Date dateTime = sdf.parse(date);
			c.setTime(dateTime);
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}

			now = sdf.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return now;
	}

}