
package com.zqm.vo;

import java.util.List;

import lombok.Data;

/**
 * TODO: description
 * Date: 2019-08-06
 *
 * @author zhaqianming
 */
@Data
public class OneToManyVo {
    private int id;
    private List<User> users;

    @Data
    public static class User{
        private int newsId;
        private String userName;
        private String userTel;
    }
}
