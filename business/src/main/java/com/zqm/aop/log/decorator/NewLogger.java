
package com.zqm.aop.log.decorator;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;

import java.util.LinkedHashMap;

/**
 * TODO: description
 * Date: 2021-01-12
 *
 * @author
 */
public class NewLogger extends DecoratorLogger {

    public NewLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String value, Object request, Object response) {
        JSONObject result = getJsonObject(value, request, response);
        super.info(jsonHandle(result.toString()));
    }

    @Override
    public void info(String value, Object... arguments) {
        JSONObject result = getJsonObject(value, arguments[0], arguments[1]);
        super.info((boolean) arguments[2] ? jsonHandle(result.toString()) : result.toString());
    }

    @Override
    public void warn(String value, Object request, Object e) {
        JSONObject result = getJsonObject(value, request, null);
        super.warn(jsonHandle(result.toString()), e);
    }

    @Override
    public void warn(String value, Object... arguments) {
        JSONObject result = getJsonObject(value, arguments[0], null);
        super.warn((boolean) arguments[2] ? jsonHandle(result.toString()) : result.toString(), arguments[1]);
    }


    private JSONObject getJsonObject(String value, Object request, Object response) {
        JSONObject result = new JSONObject(new LinkedHashMap());
        result.put("description", value);
        result.put("request", request);
        if (response != null) {
            result.put("response", response);
        }
        return result;
    }

    public static String jsonHandle(String s) {
        //json 字符串
        int level = 0;
        //存放格式化的json字符串
        StringBuilder stringBuild = new StringBuilder();
        //将字符串中的字符逐个按行输出
        for (int index = 0; index < s.length(); index++)
        {
            //获取s中的每个字符
            char c = s.charAt(index);
            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0 && '\n' == stringBuild.charAt(stringBuild.length() - 1)) {
                stringBuild.append(getLevelStr(level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                case '[':
                    stringBuild.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    stringBuild.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    stringBuild.append("\n");
                    level--;
                    stringBuild.append(getLevelStr(level));
                    stringBuild.append(c);
                    break;
                default:
                    stringBuild.append(c);
                    break;
            }
        }
        return stringBuild.toString();

    }

    private static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

}
