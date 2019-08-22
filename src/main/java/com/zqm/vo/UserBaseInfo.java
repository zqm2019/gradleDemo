
package com.zqm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: description
 * Date: 2019-07-20
 *
 * @author zhaqianming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseInfo extends BaseResponse<UserBaseInfo.Data> {

    private Data data;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private int age;
    }

}
