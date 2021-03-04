
package com.zqm.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

    @Excel(name = "年龄",width = 15D)
    private int age;

    @Excel(name = "生日",width = 15D)
    private String birthDay;

    @Excel(name = "姓名",width = 15D)
    private String name;

    @Excel(name = "性别",width = 15D)
    private String sex;

    private AttributeVo attributeVo;

}
