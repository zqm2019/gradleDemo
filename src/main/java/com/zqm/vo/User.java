package com.zqm.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8843535271490853414L;
    private Integer id;
    /**
     * 用户姓名
     */

    private String username;
    /**
     * 性别
     */
    private String sex;
    private String age;
    /**
     * 生日
     */
    private Date birthday;
    private String nickName;

    private BigDecimal high;

    private String lll;

}
