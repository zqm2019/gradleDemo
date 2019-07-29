
package com.zqm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zqm.build.BuildService;
import com.zqm.dao.entity.TUserInfo;
import com.zqm.service.UserService;
import com.zqm.service.impl.PeopleService;
import com.zqm.vo.People;


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
    private UserService userService;

    @Autowired
    private BuildService buildService;

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
        return "Hello World!";
    }


    @RequestMapping(value = "update")
    public String updateUser(@RequestBody TUserInfo tUserInfo) {
        return userService.updateUserInfo(tUserInfo);
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


}
