
package com.zqm.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqm.dao.entity.TNewsInfo;
import com.zqm.dao.entity.TUserInfo;
import com.zqm.dao.mapper.OneToMany;
import com.zqm.service.UserService;
import com.zqm.vo.OneToManyVo;
import com.zqm.vo.UserBaseInfo;

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

    @Resource
    private OneToMany oneToMany;

    @RequestMapping("insert")
    public String insertUserInfo(@RequestBody TUserInfo tUserInfo) {
        return userService.insertUserInfo(tUserInfo);
    }


    @RequestMapping(value = "test")
    public UserBaseInfo testBaseResponse(Integer a){
        UserBaseInfo baseInfo =new UserBaseInfo();
        if (a != null && a==1){
            UserBaseInfo.Data data = new UserBaseInfo.Data();
            data.setAge(11);
            baseInfo.setData(data);
            return baseInfo;
        }

        if (a != null && a==2){
            String s = "ss";
            int b= Integer.valueOf(s);
        }
        return baseInfo;
    }

    @RequestMapping(value = "oneToMany")
    public OneToManyVo getOnetoMany(){
        TNewsInfo tNewsInfo =oneToMany.selectNewsInfo(1);
        return oneToMany.selectCommentByNewId();
    }
}
