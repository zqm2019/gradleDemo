
package com.zqm.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Date: 2019-08-20
 *
 * @author zhaqianming
 */
public class StringUtils {
    public static final String PHONE_PATTERN = "^1\\d{10}$";


    public static void main(String[] args) {
        System.out.println(betweenTwoDays());
        System.out.println(BigDecimal.ZERO);
        System.out.println(new BigDecimal("2.0"));
        System.out.println(new Double("0"));
        System.out.println(new BigDecimal("2.00"));
        String d = new BigDecimal("100.10").stripTrailingZeros().toPlainString();
        System.out.println(d);
        System.out.println(new BigDecimal("100.10").stripTrailingZeros().toPlainString());
    }

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 校验手机号
     */
    private static boolean isMobile(String string) {
        return Pattern.compile(PHONE_PATTERN).matcher(string).matches();
    }

    static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    private static String betweenTwoDays() {
        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);
        LocalDate birthDate = LocalDate.of(1993, Month.OCTOBER, 19);
        System.out.println("BirthDate : " + birthDate);

        Period p = Period.between(birthDate, today);
        try {
            Date date1 = simpleDateFormat.parse("2020-06-09 11:22:22");

//         先将两个时间转换为毫秒相减，得到相差的毫秒数
            Date date2 = new Date();
            long number = date2.getTime() - date1.getTime();
//         然后在将毫秒转换为date类型就可以了
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(number);
            System.out.println(number + " = " + simpleDateFormat2.format(calendar.getTime()));
            long day = number / (24 * 60 * 60 * 1000);
            long hour = (number / (60 * 60 * 1000) - day * 24);
            long min = ((number / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long a = 1/1000;
            System.out.println(a);
            System.out.println(day + "天" + hour + "小时" + min + "分");
        } catch (Exception e) {

        }
        return String.format("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());

    }
}
