package com.zqm.config;

import com.zqm.vo.AttributeVo;
import com.zqm.vo.People;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @describe:
 * @author:zqm
 */
@Component
public class TestComponent {


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
