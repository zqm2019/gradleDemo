
package com.zqm.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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


    /**
     * @DateTimeFormat注解用于指定从前台接受的时间字符串格式，若格式不对应则抛出异常。
     * @JsonFormat注解用于将Date日期格式化为指定格式的字符串。由于在序列化时间时是按照国际标准时间GMT进行格式化的，
     * 最后接受到的数据会早勒8个小时，所以应该添加timezone = "GMT+8"属性将时区设置为于国内相同的CST时区。

     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;


}
