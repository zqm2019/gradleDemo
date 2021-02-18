package com.zqm.controller;

import com.zqm.aop.log.WriteLog;
import com.zqm.vo.People;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping(value = "writeLog")
public class WriteLogTestController {

    @RequestMapping(value = "test1")
    @WriteLog
    public Object test(People people){
        return "testHello";
    }

    @RequestMapping(value = "test2")
    @WriteLog(value = "test2")
    public Object test2(People people,String string){
        return "testHello2";
    }

    @RequestMapping(value = "test3")
    @WriteLog(value = "test3", removeArg = "0")
    public Object test3(People people,String string){
        return "testHello3";
    }
}
