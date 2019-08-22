/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.utils;

/**
 * TODO: description
 * Date: 2019-08-16
 *
 * @author zhaqianming
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 日期相关工具类
 * Date: 2019-01-08
 *
 * @author zhaqianming
 */
public class DateUtils {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_DOT_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public static final DateTimeFormatter MINUTE_DOT_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    public static final DateTimeFormatter MINUTE_LINE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_GAN_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");


    /**
     * 获取第n天凌晨00点00分00秒的时间
     *
     * @return
     */
    public static Date getNextDayZeroHour(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    public static Date getOneDayEndTime(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    /**
     * 判断开始，结束时间是否大于三年，返回小于等于三年的时间
     *
     * @param startTime
     * @return
     */
    public static String eligibleTime(String startTime) {
        if (!StringUtils.isEmpty(startTime)) {
            //获取当前时间三年前的时间
            LocalDateTime complainTime = LocalDateTime.now().minus(3, ChronoUnit.YEARS);
            //判断参数时间是否在当前时间三年内
            if (toDate(complainTime).getTime() > parseDateTime(startTime).getTime()) {
                return complainTime.format(DATE_TIME_FORMATTER);
            } else {
                return startTime;
            }
        }
        return null;
    }

    //获取当天的开始时间
    public static String getDayBegin() {
        LocalDate localDate = LocalDate.now();
        return getDayStartTime(localDate);
    }

    //获取当天的结束时间
    public static String getDayEnd() {
        LocalDate localDate = LocalDate.now();
        return getDayEndTime(localDate);
    }

    //获取本周的开始时间
    public static String getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(toLocalDateTime(cal.getTime()).toLocalDate());
    }

    //获取指定时间前时间的开始时间（包含当天）
    public static String getBeginDayBefore(int dayBefore) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.minusDays(dayBefore - 1);
        return getDayStartTime(localDateTime.toLocalDate());
    }

    //获取本周的结束时间
    public static String getEndDayOfWeek() {
        String beginDayOfWeek = getBeginDayOfWeek();
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDateTime(beginDayOfWeek));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return getDayEndTime(toLocalDateTime(cal.getTime()).toLocalDate());
    }

    //获取本月的开始时间
    public static String getBeginDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthValue - 1, 1);
        return getDayStartTime(toLocalDateTime(calendar.getTime()).toLocalDate());
    }

    //获取本月的结束时间
    public static String getEndDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthValue - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(year, monthValue - 1, day);
        return getDayEndTime(toLocalDateTime(calendar.getTime()).toLocalDate());
    }

    //获取某个日期的开始时间
    public static String getDayStartTime(LocalDate localDate) {
        return localDate.atTime(LocalTime.MIN).format(DATE_TIME_FORMATTER);
    }

    //获取某个日期的结束时间
    public static String getDayEndTime(LocalDate localDate) {
        return localDate.atTime(LocalTime.MAX).format(DATE_TIME_FORMATTER);
    }

    //获取当前时间 yyyy-MM-dd HH:mm:ss
    public static String getCurrentDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            localDateTime = localDateTime.now();
        }
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    //date 转 localDateTime
    public static LocalDateTime date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return zdt.toLocalDateTime();
    }


    /**
     * 将date转成"yyyy-MM-dd HH:mm:ss"的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DATE_TIME_FORMATTER);
    }

    /**
     * 将date转成"yyyy-MM-dd"的字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATE_FORMATTER);
    }

    /**
     * 将date转成"yyyy/MM/dd"的字符串
     *
     * @param date
     * @return
     */
    public static String formatGanDate(Date date) {
        return formatDate(date, DATE_GAN_FORMATTER);
    }

    /**
     * 将date转成指定格式的字符串
     *
     * @param date
     * @param dateTimeFormatter
     * @return
     */
    private static String formatDate(Date date, DateTimeFormatter dateTimeFormatter) {
        return toLocalDateTime(date).format(dateTimeFormatter);
    }

    /**
     * 根据"yyyy-MM-dd HH:mm:ss"格式转换字符串为Date
     *
     * @param str
     * @return
     */
    public static Date parseDateTime(String str) {
        try {
            return toDate(LocalDateTime.parse(str, DATE_TIME_FORMATTER));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据"yyyy-MM-dd"格式转换字符串为Date
     *
     * @param str
     * @return
     */
    public static Date parseDate(String str) {
        try {
            return toDate(LocalDate.parse(str, DATE_FORMATTER));
        } catch (Exception e) {
            return null;
        }
    }


    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(localDateTime)));
    }

    private static Date toDate(LocalDate localDate) {
        return toDate(localDate.atStartOfDay());
    }

    public static boolean isDateValidMonth(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        boolean isValidate;

        try {
            df.setLenient(false);
            df.parse(date);
            isValidate = true;
        } catch (ParseException e) {
            isValidate = false;
        }

        return isValidate;
    }

    public static boolean isDateTime(String time, DateTimeFormatter dateTimeFormatter) {
        try {
            dateTimeFormatter.parse(time);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean aBeforeB(String a, String b, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(a, dateTimeFormatter)
                .isBefore(LocalDateTime.parse(b, dateTimeFormatter));
    }

}
