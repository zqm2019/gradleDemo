package com.zqm.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 4305013886991449314L;
    private int id;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 性别
     */
    private String sex;
    private int age;
    /**
     * 生日
     */
    private Date birthday;
    private String name;

    public Person() {
    }

    public Person(int id, String username, Date birthday) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
    }
}

