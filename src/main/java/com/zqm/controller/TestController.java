
package com.zqm.controller;

import com.alibaba.fastjson.JSON;
import com.zqm.build.BuildService;
import com.zqm.config.DesignProperties;
import com.zqm.dao.entity.TUserInfo;
import com.zqm.dao.mapper.MyselfMapper;
import com.zqm.service.UserService;
import com.zqm.service.impl.PeopleService;
import com.zqm.service.impl.UserServiceImpl;
import com.zqm.vo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * test
 * Date: 2019-07-18
 *
 * @author zhaqianming
 */
@RestController
public class TestController {


    @Autowired
    private PeopleService peopleService;
    @Autowired
    private DesignProperties designProperties;



    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @Resource(name = "userServiceImpl2")
    private UserService userService1;

    @Resource(type = UserServiceImpl.class)
    private UserService userService3;

    @Autowired
    private BuildService buildService;


    @Autowired(required = false)
    private Data data2;


    public static class Data {
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    @RequestMapping("/jj")
    public String hello(@RequestBody Data data) {
        Map<String, List<String>> map = designProperties.getOwner();
        List<String> ids= map.getOrDefault("openids", Arrays.asList("fail"));
        return JSON.toJSONString(ids);
    }


    @RequestMapping(value = "update")
    public String updateUser(@RequestBody TUserInfo tUserInfo) {
//        return userService.updateUserInfo(tUserInfo);
        return "";
    }


    @RequestMapping(value = "people")
    public People getPeople(Integer age) {
        if (age == null) {
            age = 0;
        }
        return peopleService.getPeople(age);
    }


    @RequestMapping("test/build")
    public People getBuildPeople() {
        People people = new People();
        buildService.buildPeopleAge();
        buildService.buildPeopleName().build(people, "wy");
        return people;
    }

    @Resource
    private MyselfMapper myselfMapper;

    @RequestMapping("test/mybatis")
    public Object testMybatis(Integer a) {
        myselfMapper.selectNewsInfo(a);
        System.out.println(myselfMapper.selectMap(a));
        return myselfMapper.selectMap(a);

    }


}
