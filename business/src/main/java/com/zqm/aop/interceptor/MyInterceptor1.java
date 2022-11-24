/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.aop.interceptor;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * TODO: description
 * Date: 2019-10-31
 *
 * @author zhaqianming
 */
public class MyInterceptor1 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        HanderTime methodAnnotation = method.getAnnotation(HanderTime.class);

        //1.获取目标类上的目标注解（可判断目标类是否存在该注解）
        HanderTime annotationInClass = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), HanderTime.class);
        //2.获取目标方法上的目标注解（可判断目标方法是否存在该注解）
        HanderTime annotationInMethod = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), HanderTime.class);

        if (methodAnnotation != null) {
            Long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        HanderTime methodAnnotation = method.getAnnotation(HanderTime.class);
        if (methodAnnotation != null) {
            Long startTime = (Long) request.getAttribute("startTime");
            Long endTime = System.currentTimeMillis();
            System.out.println("请求处理时间为：" + (endTime - startTime) + "ms");
            request.setAttribute("handLingTime", endTime - startTime);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }
}

