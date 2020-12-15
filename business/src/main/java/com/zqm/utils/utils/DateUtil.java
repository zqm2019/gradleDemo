
package com.zqm.utils.utils;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO: description
 * Date: 2019-04-24
 *
 * @author zhaqianming
 */
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_DOT_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public static final DateTimeFormatter MINUTE_DOT_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    public static final DateTimeFormatter MINUTE_LINE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_GAN_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static final long ONE_DAY_FOR_MILLIONS = 1000 * 60 * 60 * 24;

    public static final int ONE_WEEK_FOR_DAY = 7;

    //0.当天 1.近7天 2.近30天
    public static final int IN_THE_DAY = 0;
    public static final int IN_SEVEN_DAYS = 1;
    public static final int NEARLY_THIRTY_DAYS = 2;

    /**
     * LocateDate  转 date
     */
    public static Date LocalDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocateDateTime 转date
     */
    public static Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static boolean isDateTime(String time, DateTimeFormatter dateTimeFormatter) {
        try {
            dateTimeFormatter.parse(time);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isABeforeB(String a, String b, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(a, dateTimeFormatter)
                .isBefore(LocalDateTime.parse(b, dateTimeFormatter));
    }

    /**
     * 根据"yyyy-MM-dd HH:mm:ss"格式转换字符串为Date
     */
    public static Date parseDateTime(String str) {
        try {
            return LocalDateTimeToUdate(LocalDateTime.parse(str, DATE_TIME_FORMATTER));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将date转成"yyyy-MM-dd HH:mm:ss"的字符串
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DATE_TIME_FORMATTER);
    }

    /**
     * 将date转成指定格式的字符串
     */
    public static String formatDate(Date date, DateTimeFormatter dateTimeFormatter) {
        return toLocalDateTime(date).format(dateTimeFormatter);
    }

    /**
     * Date转LocateDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    /**
     * Date  转LocalDate
     */
    public static LocalDate dateToLocateDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime.toLocalDate();
    }

    /**
     * 相隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int intervalDay(String startDate, String endDate) {
        long time = 0L;

        try {
            time = DateUtil.SIMPLE_DATE_FORMAT_DATE.parse(endDate).getTime()
                    - DateUtil.SIMPLE_DATE_FORMAT_DATE.parse(startDate).getTime();
        } catch (ParseException e) {
            LOGGER.info("二维码日期转换异常,e=", e);
        }
        if (time > 0) {
            return (int) (time / DateUtil.ONE_DAY_FOR_MILLIONS);
        } else if (time == 0L) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 获取tab类型的时间范围：0.当天 1.近7天 2.近30天 (包含当天)
     *
     * @param type
     * @return
     */
    public static Pair<String, String> getTypeTime(int type) {
        String startTime;
        String endTime = LocalDate.now().atTime(LocalTime.MAX).format(DATE_TIME_FORMATTER);
        if (type == IN_THE_DAY) {
            startTime = LocalDate.now().atTime(LocalTime.MIN).format(DATE_TIME_FORMATTER);
        } else if (type == IN_SEVEN_DAYS) {
            startTime = LocalDate.now().minusDays(6).atTime(LocalTime.MIN).format(DATE_TIME_FORMATTER);
        } else {
            startTime = LocalDate.now().minusDays(29).atTime(LocalTime.MIN).format(DATE_TIME_FORMATTER);
        }
        return new Pair<>(startTime, endTime);
    }

    public static void main(String[] args) {
        System.out.println( LocalDate.now().minusDays(6).atTime(LocalTime.MIN).format(DATE_TIME_FORMATTER));
    }


    /**
     * 获取第n天开始时间，任意整点点00分00秒的时间
     *
     * @param date   指定日期
     * @param amount 表示与指定日期相隔amount天，正数往前推amount天，负数指定日期往后推 0表示与指定日期相同一天
     * @param hour   指定的时间
     * @return
     */
    public static String getOneDayStartTime(Date date, int amount, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, amount);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

}
