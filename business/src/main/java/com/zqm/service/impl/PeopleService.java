
package com.zqm.service.impl;

import org.springframework.stereotype.Service;

import com.zqm.vo.People;

/**
 * TODO: description
 * Date: 2019-07-29
 *
 * @author zhaqianming
 */
@Service
public class PeopleService {

    public People getPeople(int age) {
        People people;
        if (age > 0) {
            people = People.builder()
                    .age(11)
                    .birthDay("12.05")
                    .name("zqm")
                    .build();
        } else {
            people = People.builder()
                    .build();
        }

        return people;
    }




}
