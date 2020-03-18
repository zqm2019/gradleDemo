package com.zqm.controller;

import com.zqm.utils.OrikaMapper;
import com.zqm.vo.Person;
import com.zqm.vo.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;

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
        user.setSex("man");
        user.setUsername("Tison");
//        user.setAddress("address");
        user.setBirthday(new Date());
        user.setAge("1");
        user.setNickName(1111);

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
        user.setId(1);
        user.setSex("man");
        user.setUsername("Tison");
//        user.setAddress("address");
        user.setBirthday(new Date());
        user.setAge("1");

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

