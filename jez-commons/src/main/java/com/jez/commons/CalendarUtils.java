package com.jez.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JEZ on 2017/5/8.
 */
public class CalendarUtils {

  private CalendarUtils() {
  }

  public static final String DATE_PATTERN = "yyyy-MM-dd";
  public static final String TIME_PATTERN = "HH:mm:ss";
  public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  public static Date getNow() {
    return new Date();
  }

  public static SimpleDateFormat createSimpleDateFormat(String pattern, boolean lenient) {
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    format.setLenient(lenient);
    return format;
  }

  public static SimpleDateFormat createSimpleDateFormat(String pattern) {
    return createSimpleDateFormat(pattern, false);
  }

  public static String format(Date date, String pattern) {
    if (date == null || pattern == null) {
      return null;
    }
    return createSimpleDateFormat(pattern).format(date);
  }

  public static String formatAsDate(Date date) {
    return format(date, DATE_PATTERN);
  }

  public static String formatAsTime(Date date) {
    return format(date, TIME_PATTERN);
  }

  public static String formatAsDatetime(Date date) {
    return format(date, DATETIME_PATTERN);
  }

  public static String formatNow(String pattern) {
    return format(getNow(), pattern);
  }

  public static String getDateOfNow() {
    return formatNow(DATE_PATTERN);
  }

  public static String getTimeOfNow() {
    return formatNow(TIME_PATTERN);
  }

  public static String getDatetimeOfNow() {
    return formatNow(DATETIME_PATTERN);
  }

  public static Date parse(String src, String pattern, boolean lenient) {
    if (src == null || pattern == null) {
      return null;
    }
    try {
      return createSimpleDateFormat(pattern, lenient).parse(src);
    } catch (ParseException e) {
      return null;
    }
  }

  public static Date parseDate(String src, boolean lenient) {
    return parse(src, DATE_PATTERN, lenient);
  }

  public static Date parseTime(String src, boolean lenient) {
    return parse(src, TIME_PATTERN, lenient);
  }

  public static Date parseDatetime(String src, boolean lenient) {
    return parse(src, DATETIME_PATTERN, lenient);
  }

  public static Date parse(String src, String pattern) {
    return parse(src, pattern, false);
  }

  public static Date parseDate(String src) {
    return parse(src, DATE_PATTERN);
  }

  public static Date parseTime(String src) {
    return parse(src, TIME_PATTERN);
  }

  public static Date parseDatetime(String src) {
    return parse(src, DATETIME_PATTERN);
  }

  public static Calendar getCalendar(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  public static Calendar getCalendar() {
    return Calendar.getInstance();
  }

  public static void set(Calendar calendar, int field, int value) {
    if (calendar != null) {
      calendar.set(field, value);
    }
  }

  public static void setYear(Calendar calendar, int value) {
    set(calendar, Calendar.YEAR, value);
  }

  public static void setMonth(Calendar calendar, int value) {
    set(calendar, Calendar.MONTH, value);
  }

  public static void setDay(Calendar calendar, int value) {
    set(calendar, Calendar.DAY_OF_MONTH, value);
  }

  public static void setHour(Calendar calendar, int value) {
    set(calendar, Calendar.HOUR_OF_DAY, value);
  }

  public static void setMinute(Calendar calendar, int value) {
    set(calendar, Calendar.MINUTE, value);
  }

  public static void setSecond(Calendar calendar, int value) {
    set(calendar, Calendar.SECOND, value);
  }

  public static void setMillisecond(Calendar calendar, int value) {
    set(calendar, Calendar.MILLISECOND, value);
  }

  private static void setAsDayBeginning(Calendar calendar) {
    setHour(calendar, 0);
    setMinute(calendar, 0);
    setSecond(calendar, 0);
    setMillisecond(calendar, 0);
  }

  private static void setAsDayEndding(Calendar calendar) {
    setHour(calendar, 23);
    setMinute(calendar, 59);
    setSecond(calendar, 59);
    setMillisecond(calendar, 999);
  }

  public static Date getDayBeginning(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = getCalendar(date);
    setAsDayBeginning(calendar);
    return calendar.getTime();
  }

  public static Date getDayEndding(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = getCalendar(date);
    setAsDayEndding(calendar);
    return calendar.getTime();
  }

  public static Date getMonthBeginning(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = getCalendar(date);
    setDay(calendar, 1);
    setAsDayBeginning(calendar);
    return calendar.getTime();
  }

  public static Date getMonthEndding(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = getCalendar(date);
    setDay(calendar, 1);
    calendar.roll(Calendar.DAY_OF_MONTH, -1);
    setAsDayEndding(calendar);
    return calendar.getTime();
  }

  public static Date getYearBeginning(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = getCalendar(date);
    setMonth(calendar, 0);
    setDay(calendar, 1);
    setAsDayBeginning(calendar);
    return calendar.getTime();
  }

  public static Date getYearEndding(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = getCalendar(date);
    setMonth(calendar, 11);
    setDay(calendar, 31);
    setAsDayEndding(calendar);
    return calendar.getTime();
  }

  public static void add(Calendar calendar, int field, int amount) {
    if (calendar != null) {
      calendar.add(field, amount);
    }
  }

  public static void addYear(Calendar calendar, int amount) {
    add(calendar, Calendar.YEAR, amount);
  }

  public static void addMonth(Calendar calendar, int amount) {
    add(calendar, Calendar.MONTH, amount);
  }

  public static void addDay(Calendar calendar, int amount) {
    add(calendar, Calendar.DAY_OF_MONTH, amount);
  }

  public static void addHour(Calendar calendar, int amount) {
    add(calendar, Calendar.HOUR_OF_DAY, amount);
  }

  public static void addMinute(Calendar calendar, int amount) {
    add(calendar, Calendar.MINUTE, amount);
  }

  public static void addSecond(Calendar calendar, int amount) {
    add(calendar, Calendar.SECOND, amount);
  }

  public static void addMillisecond(Calendar calendar, int amount) {
    add(calendar, Calendar.MILLISECOND, amount);
  }

}
