package com.zqm.vo;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8843535271490853414L;
    private Integer id;
    @Nullable
    /**
     * 用户姓名
     */

    private String username;
    @NotNull
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
