
package com.zqm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * people实体类
 * Date: 2019-07-29
 *
 * @author zhaqianming
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class People {

    private int age;

    private String birthDay;

    private String name;

    private String sex;

}
