package com.zqm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @describe:
 * @author:zqm
 */
@Aspect
@Component
public class AopDemo {
    @Pointcut(value = "execution(* com.zqm.*.*.*(..))")
    public void point() {
    }

    @Before(value = "point()")
    public void before() {
        System.out.println("transaction begin");
    }

    @AfterReturning(value = "point()")
    public void after() {
        System.out.println("transaction commit");
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("transaction begin");
        joinPoint.proceed();
        System.out.println("transaction commit");
    }
}
