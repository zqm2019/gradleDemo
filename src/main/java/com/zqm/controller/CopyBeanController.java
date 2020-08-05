package com.zqm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zqm.utils.OrikaMapper;
import com.zqm.vo.Person;
import com.zqm.vo.User;
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
    private OrikaMapper orikaMapper;

    @RequestMapping("beanCopy")
    public void test() {
        User user = new User();
        user.setId(1);
//        user.setSex("man");
        user.setUsername("Tison");
//        user.setAddress("address");
        user.setBirthday(new Date());
        user.setAge("1");
//        user.setNickName();
        System.out.println(JSONObject.parseObject(JSON.toJSONString(user)));

        Person target = new Person();
        BeanUtils.copyProperties(user, target);
        System.out.println(user.toString());
        System.out.println(target.toString());

        try {
            Person target1 = orikaMapper.convert(user, Person.class);
            System.out.println(user.toString());
            System.out.println(target1.toString());
        } catch (Exception e) {
            log.error("fdfa" + e);
        }
    }

    public static void main(String[] args) {


        User user = new User();
        user.setId(1);
//        user.setSex("man");
        user.setUsername("Tison");
//        user.setAddress("address");
        user.setBirthday(new Date());
        user.setAge("1");

        System.out.println("1：" + JSONObject.toJSONString(user));
        System.out.println("2：" + JSONObject.parseObject(JSON.toJSONString(user)));
        System.out.println("3：" + JSON.parseObject(JSON.toJSONString(user)));

        JSONObject jsonObject2 = JSON.parseObject(JSON.toJSONString(user));
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(user));
        System.out.println("4：" + jsonObject2.getString("username"));
        System.out.println("5：" + jsonObject2.get("username"));

        System.out.println("6：" + jsonObject.getString("username"));

        List<User> kk = new ArrayList<>();
        kk.add(user);
        System.out.println("7：" + JSONObject.toJSONString(kk));
        //通过json转数组去掉一些无用的键值对
        System.out.println("8：" + JSON.parseArray(JSONObject.toJSONString(kk)));
        System.out.println("9：" + JSONObject.parseArray(JSONObject.toJSONString(kk)));
        List<User> users = JSON.parseArray(JSONObject.toJSONString(kk),User.class);
        System.out.println(users);


//
//        Person target=new Person();
//        BeanUtils.copyProperties(user,target);
//        System.out.println(user.toString());
//        System.out.println(target.toString());

//        try {
//            Person  target1 = orikaMapper.convert(user,Person.class);
//            System.out.println(user.toString());
//            System.out.println(target1.toString());
//        }catch (Exception e){
//            log.error("fdfa"+e);
//        }


    }

}

