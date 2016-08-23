package com.infy.jcart.resources;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;


/**
 *
 * This class uses advanced Third Party Library called Joda-Time to do 
 * operations on Date and calendar objects.
 * This avoids errors due to locale differences and other errors. 
 */
public class CalendarUtility {

	public static Calendar getCalendarFromString(String date,String pattern) throws Exception{
		SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
		Date dateReference = null;
		Calendar calendar = Calendar.getInstance();
		try {
			dateReference = simpleDate.parse(date);
			calendar.setTime(dateReference);
			return calendar;
		} catch (ParseException e) {
			JCartLogger.logError("CalendarUtility",
					"getCalendarFromString", e.toString());
			throw new Exception("Error occured while parsing date :"+e.getMessage());
		}
	}

	public static String getStringFromCalendar(Calendar calendar,String pattern)
			throws Exception {
		Date date = null;
		SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
		date = calendar.getTime();
		String textDate = simpleDate.format(date);
		return textDate;
	}
	
	public static Integer getDaysBetweenDates(Calendar from, Calendar to) throws Exception{
		DateTime start = new DateTime(from.getTime());
		DateTime end = new DateTime(to.getTime());
		Period p = new Period(start, end, PeriodType.yearDay());
		int years = p.getYears();
		int days = p.getDays();
		return (years*365+days);
	}
	
	/**
	 * Joda Time functionality gives output in this format when converted to normal Period.
	 * 0 years, 0 months, 1 weeks, 3 days
	 * 21 hours, 26 minutes, 30 seconds 
	 */
	public static Period getIntervalBetweendates(Calendar from, Calendar to) throws Exception{
		DateTime start = new DateTime(from.getTime());
		DateTime end = new DateTime(to.getTime());
		Interval interval = new Interval(start, end);
		return interval.toPeriod();
	}
	
	/**
	 * Joda Time functionality gives output in this format
	 * 0 years, 0 months, 0 weeks, 10days
	 * 21 hours, 26 minutes, 30 seconds 
	 */
	public static Period getPeriodBetweenDates(Calendar from, Calendar to) throws Exception{
		DateTime start = new DateTime(from.getTime());
		DateTime end = new DateTime(to.getTime());
		Period p = new Period(start, end, PeriodType.yearMonthDayTime());
		return p;
	}
	public static Calendar addDaystoMidnightDate(Calendar d, int days) throws Exception {
		DateTime d1 = new DateTime(d);
		Calendar c = Calendar.getInstance();
		d1 = d1.toDateMidnight().toDateTime();
		c.setTime(d1.plusDays(days).toDate());
		return c;
	}

	/**
	 *  Add no of days to a given date.
	 */
	public static Calendar addDaystoDate(Calendar d, int days) throws Exception {
		DateTime d1 = new DateTime(d);
		Calendar c = Calendar.getInstance();
		c.setTime(d1.plusDays(days).toDate());
		return c;
	}
}

