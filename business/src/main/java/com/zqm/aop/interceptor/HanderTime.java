/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.aop.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: description
 * Date: 2019-11-10
 *
 * @author zhaqianming
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HanderTime {
}
