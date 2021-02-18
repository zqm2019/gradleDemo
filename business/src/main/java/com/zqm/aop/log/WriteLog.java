package com.zqm.aop.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe:
 * @author:zqm
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WriteLog {

    /**
     * 自定义日志前缀,默认为全类名+方法名
     */
    String value() default "";

    /**
     * 只有在异常时打印日志标志
     */
    boolean onlyException() default false;

    /**
     * 入参移除不在打印日志中，从0开始
     */
    String removeArg() default "";

    /**
     * 是否输出json格式
     */
    boolean jsonFormat() default true;

    /**
     * 异常后是否抛异常，默认抛
     */
    boolean throwException() default true;
}
