package com.zqm.controller;

import com.zqm.config.TestThisComponent;
import com.zqm.vo.AttributeVo;
import com.zqm.vo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("config")
public class TestConfig {

    @Autowired
    private AttributeVo attributeVo;

    @Autowired
    private People people;

    @Autowired
    private TestThisComponent asyncService;


    @RequestMapping("testConfig")
    public String contextLoads() {
        boolean result = people.getAttributeVo() == attributeVo;
        return result ? "同一个对象" : "不同的对象";
    }


    @RequestMapping("testThis")
    public void testAsync() {
        asyncService.async1();
        asyncService.async2();
    }



}
