
package com.zqm.utils;

import java.math.BigDecimal;
import java.sql.SQLOutput;

/**
 * 字符串工具类
 * Date: 2019-08-20
 *
 * @author zhaqianming
 */
public class StringUtils {

    public static void main(String[] args) {
        System.out.println(BigDecimal.ZERO);
        System.out.println(new BigDecimal("2.0"));
        System.out.println(new Double("0"));
        System.out.println(new BigDecimal("2.00"));
        String d = new BigDecimal("100.10").stripTrailingZeros().toPlainString();
        System.out.println(d);
        System.out.println(new BigDecimal("100.10").stripTrailingZeros().toPlainString());
    }
}
