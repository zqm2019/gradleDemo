package com.zqm.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8843535271490853414L;
    private int id;
    private String username;// 用户姓名
    private String sex;// 性别
    private String age;
    private Date birthday;// 生日
    private int nickName;



}
