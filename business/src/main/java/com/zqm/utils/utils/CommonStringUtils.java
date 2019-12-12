
package com.zqm.utils.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 字符串处理工具
 * Date: 2019-10-28
 *
 * @author zhuwei8
 */
public class CommonStringUtils {


    /**
     * 将字符串转为list
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<String> convertStrToList(String str, String regex) {
        if (org.springframework.util.StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        String[] strings = str.split(regex);
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            result.add(s);
        }
        return result;
    }

    /**
     * 生成uuid
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
