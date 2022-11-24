package com.zqm.config;

import com.zqm.vo.AttributeVo;
import com.zqm.vo.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe:
 * ConfigurationClassPostProcessor类之中，通过调用enhanceConfigurationClasses方法，为被注解@Configuration的类进行CGLIB代理
 * 生成当前对象的子类Class，并对方法拦截，第二次调用car()方法时直接从BeanFactory之中获取对象，所以得到的是同一个对象
 * @author:zqm
 */
@Configuration
public class TestConfiguration {


    @Bean
    public People driver(){
        People driver = new People();
        driver.setName("driver");
        driver.setAttributeVo(attributeVo());
        return driver;
    }

    @Bean
    public AttributeVo attributeVo(){
        AttributeVo attributeVo = new AttributeVo();
        attributeVo.setAttributeId(1);
        return attributeVo;
    }

}
