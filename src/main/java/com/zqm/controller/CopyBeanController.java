package com.zqm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zqm.utils.OrikaMapper;
import com.zqm.vo.Person;
import com.zqm.vo.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @describe: 复制bean的集中方法比较
 * 1.beanUtil.copyProperties(source,target)
 * 2.orika
 * @author:zqm
 */
@Slf4j
@RestController
public class CopyBeanController {
    @Autowired
    private  OrikaMapper orikaMapper;

    @RequestMapping("beanCopy")
    public void test(){
        User user=new User();
        user.setId(1);
//        user.setSex("man");
        user.setUsername("Tison");
//        user.setAddress("address");
        user.setBirthday(new Date());
        user.setAge("1");
//        user.setNickName();
        System.out.println(JSONObject.parseObject(JSON.toJSONString(user)));

        Person target=new Person();
        BeanUtils.copyProperties(user,target);
        System.out.println(user.toString());
        System.out.println(target.toString());

        try {
            Person  target1 = orikaMapper.convert(user,Person.class);
            System.out.println(user.toString());
            System.out.println(target1.toString());
        }catch (Exception e){
            log.error("fdfa"+e);
        }
    }

    public static void main(String[] args) {


        User user=new User();
//        user.setId(1);
//        user.setSex("man");
        user.setUsername("Tison");
//        user.setAddress("address");
        user.setBirthday(new Date());
        user.setAge("1");

        System.out.println(JSONObject.toJSONString(user));
        System.out.println(JSONObject.parseObject(JSON.toJSONString(user)));

        List<User> kk = new ArrayList<>();
        kk.add(user);
        System.out.println(JSONObject.toJSONString(kk));
        //通过json转数组去掉一些无用的键值对
        System.out.println(JSON.parseArray(JSONObject.toJSONString(kk)));



        Person target=new Person();
        BeanUtils.copyProperties(user,target);
        System.out.println(user.toString());
        System.out.println(target.toString());

//        try {
//            Person  target1 = orikaMapper.convert(user,Person.class);
//            System.out.println(user.toString());
//            System.out.println(target1.toString());
//        }catch (Exception e){
//            log.error("fdfa"+e);
//        }


    }


}

