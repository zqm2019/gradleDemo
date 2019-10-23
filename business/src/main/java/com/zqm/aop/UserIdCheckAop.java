/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * TODO: description
 * Date: 2019-10-23
 *
 * @author zhaqianming
 */
@Aspect
@Component
@Order(1)
public class UserIdCheckAop {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义切点
     */
    @Pointcut(value = "@annotation(com.zqm.aop.AutoCheckUser)")
    public void cut() {

    }
 private static final List list = Arrays.asList("123","345","789");
    /**
     * 拦截器实现
     */

    @Around("cut()")
    public Object FilterUser(ProceedingJoinPoint joinPoint) {
        Object result = null;
        Object[] args = joinPoint.getArgs();
        try {
            //访问目标方法的参数：
            if (args != null && args.length > 1) {
                String userId = String.valueOf(args[1]);
                if (StringUtils.isEmpty(userId)) {
                    return "未登录";
                }
                if (!list.contains(userId)){
                    return "为授权用户";
                }
            }

            //服务调用执行
            result = joinPoint.proceed(args);
        } catch (Exception e) {
            result = e.getMessage();
        } catch (Throwable throwable) {
            result = throwable.getMessage();
        }
        return result;

    }

}
