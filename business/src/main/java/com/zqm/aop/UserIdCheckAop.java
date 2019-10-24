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
@Aspect //定义一个切面
@Component // 把普通pojo实例化到spring容器中
@Order(1) //多个注解是优先级最高，值越小优先级越高
public class UserIdCheckAop {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义切点 切点也可以直接写在通知的value层
     */
    @Pointcut(value = "@annotation(com.zqm.aop.AutoCheckUser)")
    public void cut() {

    }
 private static final List list = Arrays.asList("123","345","789");


    /*
    可自定义切点位置，针对不同切点，方法上的@Around()可以这样写ex：
    @Around(value = "methodPointcut() && args(..)")
    @Pointcut(value = "@annotation(com.rq.aop.common.annotation.MyAnnotation)")
    public void methodPointcut(){}


    @Pointcut(value = "@annotation(com.rq.aop.common.annotation.MyAnnotation2)") //在所有标记@MyAnnotation2方法上切入
    public void methodPointcut2(){}
    */

    /**
     * 拦截器实现
     */

    @Around("cut()")
    public Object FilterUser(ProceedingJoinPoint joinPoint) {
        Object result;
        Object[] args = joinPoint.getArgs();
        try {
            //访问目标方法的参数：
            if (args != null && args.length > 1) {
                String userId = String.valueOf(args[1]);
                if (StringUtils.isEmpty(userId)) {
                    return "未登录";
                }
                if (!list.contains(userId)){
                    return "未授权用户";
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

    /**
     * //execution（）                表达式的主体；
     * 第一个”*“符号                   表示返回值的类型任意；
     * com.zqm.service.impl           AOP所切的服务的包名，即，我们的业务部分
     *  包名后面的”..“                  表示当前包及子包
     *  第二个”*“                      表示类名，*即所有类。此处可以自定义
     *  .*(..)                        表示任何方法名，括号表示参数，两个点表示任何参数类型
     * @param point
     * @return
     * @throws Throwable
     */

    //只定义切面
    @Around("execution(* com.zqm.service.impl..*.*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
            args[0] = "改变后的参数1";
        }
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return "原返回值：" + returnValue + "，这是返回结果的后缀";
    }



}
