package com.zqm.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("shrio")
public class ShrioController {

    @RequestMapping("test")
    @RequiresPermissions(value = "dd")
    public String testShiro(){
        return "success";
    }
}
