package com.zqm.utils.utils;

import java.time.format.DateTimeFormatter;

/**
 * 一般常量
 * Date: 2019-04-24
 *
 * @author zhaqianming
 */
public class Constant {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FORMATTER_HOUR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_DOT_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public static final DateTimeFormatter MINUTE_DOT_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    public static final DateTimeFormatter MINUTE_LINE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_GAN_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
}