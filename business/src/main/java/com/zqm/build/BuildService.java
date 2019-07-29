
package com.zqm.build;

import org.springframework.stereotype.Component;

import com.zqm.vo.People;

/**
 * TODO: description
 * Date: 2019-07-29
 *
 * @author zhaqianming
 */
@Component
public class BuildService {

    public TestBuild<People, String> buildPeopleName() {
        return (people, name) -> {
            people.setName(name);
        };
    }

    public TestBuild<People, Integer> buildPeopleAge() {
        return (people, age) -> {
            people.setAge(age);
        };
    }


}
