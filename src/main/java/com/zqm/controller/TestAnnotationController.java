/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqm.aop.AutoCheckUser;

/**
 * test@PostConstruct
 * <p>
 * Date: 2019-08-23
 *
 * @author zhaqianming
 */
@RestController
@RequestMapping("annotation")
public class TestAnnotationController implements ApplicationContextAware {

    private String a;

    private ApplicationContext ApplicationContext;


    private TestAnnotationController testAnnotationController;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ApplicationContext = applicationContext;
    }

    /**
     * @PostConstruct 修饰的方法在spring启动的时候会被执行一次
     */
//    public @PostConstruct void init() {
//        a = "被初始化了";
//        testAnnotationController = ApplicationContext.getBean(TestAnnotationController.class);
//        System.out.println();
//    }

    /**
     * 建议此写法
     */
    @PostConstruct
    public void init() {
        a = "被初始化了";
        testAnnotationController = ApplicationContext.getBean(TestAnnotationController.class);
        System.out.println();
    }

    @RequestMapping("test")
    public String testAnnotation() {
        return testAnnotationController.getA();
        // return a;
    }


    private String getA() {
        return a;
    }


    @RequestMapping("testAop")
    @AutoCheckUser
    public String checkUser(String userId, String user) {
        return "true";
    }
}
