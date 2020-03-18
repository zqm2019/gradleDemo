package com.zqm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

/**
 * @describe:从Function了解函数式编程
 * public interface Function<F, T> {
 *
 *   T apply(@Nullable F input);
 *
 *   boolean equals(@Nullable Object object);
 * }
 * 该接口提供两个方法,一个是apply,一个是equals.apply方法接受一个input参数返回T。
 * 该接口主要用于转换函数。给我一个输入,我转换为另外需要的输出。
 * @author:zqm
 */
public class FunctionController {

    public static void main(String[] args) {
        DateFunction dateFunction = new DateFunction();
        System.out.println(dateFunction.apply("1220-24-34"));
        System.out.println(new Function<String,Date>() {

            /**
             * Applies this function to the given argument.
             *
             * @param s the function argument
             * @return the function result
             */
            @Override
            public Date apply(String s) {
                try {
                    return new SimpleDateFormat("yyyy-MM-dd").parse("2233-44-44");
                } catch (ParseException e) {
                    return null;
                }

        }
    });


    }
}

/**
 * 将一个string转成data格式
 */
class DateFunction implements Function<String, Date> {

    @Override
    public Date apply(String input) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (ParseException e) {

        }

        return null;
    }

}
