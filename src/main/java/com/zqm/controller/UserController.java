
package com.zqm.controller;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.zqm.dao.entity.TNewsInfo;
import com.zqm.dao.entity.TUserInfo;
import com.zqm.dao.mapper.OneToMany;
import com.zqm.dao.mapper.TCommentInfoMapper;
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
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Resource
    private OneToMany oneToMany;

    @RequestMapping("insert")
    public String insertUserInfo(@RequestBody TUserInfo tUserInfo) {
        System.out.println(tUserInfo.getCreateTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(simpleDateFormat.format(tUserInfo.getCreateTime()));
        try{
        tUserInfo.setCreateTime(simpleDateFormat.parse(simpleDateFormat.format(tUserInfo.getCreateTime())));

        }catch (Exception e){

        }
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

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        List<List<String>> aa = Lists.partition(a,200);
        System.out.println(aa);
    }
}
