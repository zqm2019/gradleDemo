/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: description
 * Date: 2019-10-23
 *
 * @author zhaqianming
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AutoCheckUser {
}
