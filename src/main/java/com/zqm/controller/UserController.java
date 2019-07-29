
package com.zqm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqm.dao.entity.TUserInfo;
import com.zqm.service.UserService;

/**
 * TODO: description
 * Date: 2019-07-20
 *
 * @author zhaqianming
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("insert")
    public String insertUserInfo(@RequestBody TUserInfo tUserInfo) {
        return userService.insertUserInfo(tUserInfo);
    }
}
