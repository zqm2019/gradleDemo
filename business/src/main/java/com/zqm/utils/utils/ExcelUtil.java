
package com.zqm.utils.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * excel支持类
 * Date: 2018-11-15
 *
 * @author chenxi
 */
@Service
public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 创建一个二维的excel数据表
     *
     * @param dataTable
     * @return
     */
    public HSSFWorkbook createExcel(List<List<String>> dataTable) {
        LOGGER.info("create excel start");
        if (CollectionUtils.isEmpty(dataTable)) {
            LOGGER.warn("数据表为空");
            return null;
        }

        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建页
        HSSFSheet sheet = workbook.createSheet();
        //设置单元格属性
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        for (int i = 0; i < dataTable.size(); i++) {
            if (dataTable.get(i) == null) {
                continue;
            }
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < dataTable.get(i).size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(dataTable.get(i).get(j));
            }
        }
        LOGGER.info("create excel end");
        return workbook;
    }

    /**
     * 创建微信群列表的excel数据表
     *
     * @param dataTable
     * @return
     */
    public HSSFWorkbook createWxGroupListExcel(List<List<String>> dataTable) {
        LOGGER.info("create excel start");
        if (CollectionUtils.isEmpty(dataTable)) {
            LOGGER.warn("数据表为空");
            return null;
        }

        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建页
        HSSFSheet sheet = workbook.createSheet();
        //设置单元格属性
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        for (int i = 0; i < dataTable.size(); i++) {
            if (dataTable.get(i) == null) {
                continue;
            }
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < dataTable.get(i).size(); j++) {
                HSSFCell cell = row.createCell(j);
                if (i !=0 && (j == 4 || j == 6 || j == 8 || j == 11 || j == 12 || j == 13 || j == 14 || j == 15)){
                    if (j == 11 || j == 12 || j == 13 || j == 14 || j == 15){
                        cell.setCellValue(NumberUtils.toInt(dataTable.get(i).get(j)));
                    }else {
                        String data = dataTable.get(i).get(j);
                        if (!StringUtils.isEmpty(data)){
                            cell.setCellValue(NumberUtils.toInt(dataTable.get(i).get(j)));
                        }else {
                            cell.setCellValue(data);
                        }
                    }
                }else {
                    cell.setCellValue(dataTable.get(i).get(j));
                }
            }
        }
        LOGGER.info("create excel end");
        return workbook;
    }
}
