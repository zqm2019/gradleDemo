/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.aop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO: description
 * Date: 2019-10-31
 *
 * @author zhaqianming
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public MyInterceptor1 myInterceptor1() {
        return new MyInterceptor1();
    }

    @Bean
    public MyInterceptor2 myInterceptor2() {
        return new MyInterceptor2();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(myInterceptor2()).addPathPatterns("/**");
//        registry.addInterceptor(myInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(myInterceptor1());
        super.addInterceptors(registry);
    }
}
