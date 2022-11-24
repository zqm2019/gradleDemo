/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.aop;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: description
 * Date: 2019-10-23
 *
 * @author zhaqianming
 */
@Aspect //定义一个切面
@Component // 把普通pojo实例化到spring容器中,否则将扫描不到切面类
@Order(1) //多个注解是优先级最高，值越小优先级越高
public class UserIdCheckAop {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Logger LOGGER;

    /**
     * 定义切点 切点也可以直接写在通知的value层
     */
    @Pointcut(value = "@annotation(com.zqm.aop.AutoCheckUser)")
    public void cut() {

    }

    private static final List list = Arrays.asList("123", "345", "789");


    /*
    可自定义切点位置，针对不同切点，方法上的@Around()可以这样写ex：
    @Around(value = "methodPointcut() && args(..)")
    @Pointcut(value = "@annotation(com.rq.aop.common.annotation.MyAnnotation)")
    public void methodPointcut(){}

    //在所有标记@MyAnnotation2方法上切入
    @Pointcut(value = "@annotation(com.rq.aop.common.annotation.MyAnnotation2)")
    public void methodPointcut2(){}
    */

    /**
     * 拦截器实现
     * ProceedingJoinPoint获取当前方法joinPoint.getTarget().getClass().getName()和参数getArgs()
     * 环绕通知 ProceedingJoinPoint 执行proceed方法的作用是让目标方法执行，这也是环绕通知和前置、后置通知方法的一个最大区别
     */

    @Around("cut()")
    public Object FilterUser(ProceedingJoinPoint joinPoint)throws Throwable {
        Object result;
        Object[] args = joinPoint.getArgs();
//        try {
            //访问目标方法的参数：
            if (args != null && args.length > 1) {
                String userId = String.valueOf(args[1]);
                if (StringUtils.isEmpty(userId)) {
                    return "未登录";
                }
                if (!list.contains(userId)) {
//                    throw new RuntimeException("dd");
                    return "未授权用户";
                }
            }

            //服务调用执行
            result = joinPoint.proceed(args);
//        } catch (Exception e) {
//            result = e.getMessage();
//        } catch (Throwable throwable) {
//            result = throwable.getMessage();
//        }
        return result;

    }


    /**
     * Aop中的额异常必须是RuntimeException异常或其子类否则抛出UndeclaredThrowableException异常。
     * 下面例子放开可测试
     */
//    @Around("cut()")
//    public Object FilterUser(ProceedingJoinPoint joinPoint)throws Throwable {
//        Object result;
//        Object[] args = joinPoint.getArgs();
////        try {
//        //访问目标方法的参数：
//        if (args != null && args.length > 1) {
//            String userId = String.valueOf(args[1]);
//            if (StringUtils.isEmpty(userId)) {
//                return "未登录";
//            }
//            if (!list.contains(userId)) {
//                throw new Exception("dd");
//                 throw new RuntimeException("dd");
////                    return "未授权用户";
//            }
//        }
//
//        //服务调用执行
//        result = joinPoint.proceed(args);
////        } catch (Exception e) {
////            result = e.getMessage();
////        } catch (Throwable throwable) {
////            result = throwable.getMessage();
////        }
//        return result;
//
//    }

    /**
     * //execution（）                表达式的主体；
     * 第一个”*“符号                   表示返回值的类型任意；*号跟一个空格 必不可少
     * com.zqm.service.impl           AOP所切的服务的包名，即，我们的业务部分
     * 包名后面的”..“                  表示当前包及子包
     * 第二个”*“                      表示类名，*即所有类。此处可以自定义
     * .*(..)                        表示任何方法名，括号表示参数，两个点表示任何参数类型
     *
     *  && 且操作
     *  !execution表示除外
     *
     * @param point
     * @return
     * @throws Throwable
     */

    //只定义切面
    @Around("execution(* com.zqm.service.impl..*.*(..)) &&  !execution(* com.zqm.service.impl..PeopleService.*(..))")
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


    @Pointcut("@annotation(autoCheckUser)")
    public void intercept(AutoCheckUser autoCheckUser) {
    }

    //校验用户是否有权限可以操作群时，方法入参必须填写的属性群id
    public static final String PROPERY_NAME_ROOM_ID = "roomId";

    //校验用户是否有权限可以操作群时，方法入参必须填写的属性用户名称
    public static final String PROPERY_NAME_OPER_USERNAME = "operatorName";

    @Before(value = "intercept(checkRoomOperAuth)", argNames = "joinPoint,checkRoomOperAuth")
    public void doBefore(JoinPoint joinPoint, AutoCheckUser checkRoomOperAuth) throws Exception {
        Class targetClass = joinPoint.getTarget().getClass();
        LOGGER = LoggerFactory.getLogger(targetClass);
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();
        Object object = joinPoint.getArgs()[0];
        try {
            String roomId = BeanUtils.getProperty(object, PROPERY_NAME_ROOM_ID);
            String operatorName = BeanUtils.getProperty(object, PROPERY_NAME_OPER_USERNAME);
            if (!StringUtils.isEmpty(roomId) && !StringUtils.isEmpty(operatorName)) {
                //check方法
                boolean isUserCanOperRoom = false;
//                boolean isUserCanOperRoom = managerService.checkIsUserCanOperRoom(roomId, operatorName);
                if (!isUserCanOperRoom) {
                    throw new Exception("");
                }
            } else {
                LOGGER.warn("roomId: " + roomId + " operatorName:" + operatorName + "args empty!");
                throw new Exception("");
            }
        } catch (Exception ex) {
            LOGGER.error(methodName + " args empty!");
            throw new Exception("");
        }

    }


}
