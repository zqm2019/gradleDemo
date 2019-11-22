/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解: 注解的注解，只作用在注解身上
 *
 * @author zhaqianming
 * @Retention 应用到一个注解上的时候，它解释说明了这个注解的的存活时间
 *      SOURCE参数只在源码阶段保留，在编译器进行编译时它将被丢弃忽视
 *      CLASS注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 *      RUNTIME(最久) 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们
 *
 * @Target 指定了注解运用的地方。
 *      ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 *      ElementType.CONSTRUCTOR 可以给构造方法进行注解
 *      ElementType.FIELD 可以给属性进行注解
 *      ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 *      ElementType.METHOD 可以给方法进行注解
 *      ElementType.PACKAGE 可以给一个包进行注解
 *      ElementType.PARAMETER 可以给一个方法内的参数进行注解
 *      ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 *
 * @Documented  它的作用是能够将注解中的元素包含到 Javadoc 中去。
 *
 * @Inherited  如果一个超类被 @Inherited 注解过的注解进行注解的话，
 *             那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
 *
 * @Repeatable Repeatable 是 Java 1.8 才加进来的，所以算是一个新的特性。
 *             通常是注解的值可以同时取多个。
 *
 * Date: 2019-10-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AutoCheckUser {
}

/**
 * 检验参数的注解的实现不仅仅可以通过切面来完成，还可以通过实现HandlerInterceptor接口，覆写preHandle
 * springboot 的拦截器最常用的登录拦截、或是权限校验、或是防重复提交、或是根据业务像12306去校验购票时间
 * 方法:
 * 定义一个Interceptor 非常简单方式也有几种，我这里简单列举两种
 * 1、类要实现Spring 的HandlerInterceptor 接口
 * 2、类继承实现了HandlerInterceptor 接口的类，例如 已经提供的实现了HandlerInterceptor 接口的抽象类HandlerInterceptorAdapter
 *
 * HandlerInterceptor 接口的三个方法：
 * preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
 * postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView；
 * afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；

 */

/*
@interface Persons {
    Person[]  value();
}


@Repeatable(Persons.class)
@interface Person {
    String role
    default "";
}


@Person(role = "artist")
@Person(role = "coder")
@Person(role = "PM")
public class SuperMan {
}
*/


