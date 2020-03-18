package com.zqm.controller;

import com.google.common.base.Preconditions;
import com.zqm.vo.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RestController
public class NullController {

    @RequestMapping("testNullable")
    public String testNull(@RequestBody @Validated User user){
        //Preconditions优雅的进行判断，避免使用if()...
        Preconditions.checkArgument(user !=null,"参数不能为空");
        return "ok";
    }
}
