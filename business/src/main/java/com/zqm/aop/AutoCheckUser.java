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


