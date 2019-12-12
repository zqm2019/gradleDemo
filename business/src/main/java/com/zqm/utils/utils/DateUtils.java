package com.zqm.utils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    private DateUtils() {

    }

    /**
     * 将date转成"yyyy-MM-dd HH:mm:ss"的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, Constant.DATE_TIME_FORMATTER);
    }

    /**
     * 将date转成"yyyy-MM-dd"的字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, Constant.DATE_FORMATTER);
    }

    public static String formatDateHour(Date date) {
        return formatDate(date, Constant.DATE_FORMATTER_HOUR);
    }

    /**
     * 将date转成"yyyy/MM/dd"的字符串
     *
     * @param date
     * @return
     */
    public static String formatGanDate(Date date) {
        return formatDate(date, Constant.DATE_GAN_FORMATTER);
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
            return toDate(LocalDateTime.parse(str, Constant.DATE_TIME_FORMATTER));
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
            return toDate(LocalDate.parse(str, Constant.DATE_FORMATTER));
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

    public static LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
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

    /**
     * 获取明天的某个时间
     *
     * @param hour
     * @return
     */
    public static Date getNextDate(int hour) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 根据"yyyy-MM-dd"格式转换字符串为Calendar
     */
    public static Calendar parseTimeToCal(String time) {
        Date date = parseDate(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
