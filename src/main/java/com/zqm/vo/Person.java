package com.zqm.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 4305013886991449314L;
    private int id;
    private String username;// 用户姓名
    private String sex;// 性别
    private int age;
    private Date birthday;// 生日
    private String name;

}

