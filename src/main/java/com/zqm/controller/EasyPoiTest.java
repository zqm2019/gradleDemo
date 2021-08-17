package com.zqm.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.google.common.collect.Lists;
import com.zqm.utils.utils.ExcelUtils;
import com.zqm.vo.People;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("poi")
@Slf4j
public class EasyPoiTest {

    @RequestMapping("export/excel")
    public Object getExport(HttpServletResponse httpResponse) throws Exception{
        ExportParams exportParams = new ExportParams("lll", "导出到表格", ExcelType.XSSF);
        Workbook workbook = null;
        People people1 = new People();
        people1.setAge(12);
        people1.setBirthDay("12月5号");
        people1.setName("小明");
        People people2 = new People();
        people2.setAge(18);
        people2.setBirthDay("12月9号");
        people2.setName("小宏");
        people2.setSex("女");
        List<People> list = Lists.newArrayList(people1, people2);

        try {
            workbook = ExcelExportUtil.exportBigExcel(exportParams, People.class,list);
        } finally {
            if (workbook != null){
                //此处必须要关闭，否则会导致stream closed异常
                ExcelExportUtil.closeExportBigExcel();
            }
        }
        //try 里面的语句会自动关闭输入流的。
        try(ByteArrayOutputStream os = new ByteArrayOutputStream(1024)) {
            workbook.write(os);
            ExcelUtils.writeIO(httpResponse, os, "导出表格");
        } catch (IOException e) {
            throw new Exception("导出报表失败");
        }


        return "ok";
    }


    public static <T> T getObjectAllAttrs(Class<T> clz) throws IllegalAccessException, InstantiationException {
        T object = clz.newInstance();
        Field[] beanFiled = clz.getDeclaredFields();
        for (int z = 0; z < beanFiled.length; z++) {
            try {
                //设置为不检查
                beanFiled[z].setAccessible(true);
                //开始赋值
                beanFiled[z].set(object, "lll");
            } catch (IllegalArgumentException e) {
                log.error("失败,", e);
            }
        }
        return object;
    }

    private static final DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    public static void main(String[] args) throws Exception{
        System.out.print( "格式化结果:");
        System.out.println(decimalFormat.format(new BigDecimal("11111.0000000")));
    }

}
