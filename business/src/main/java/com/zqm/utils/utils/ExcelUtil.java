
package com.zqm.utils.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * excel支持类
 * Date: 2018-11-15
 *
 * @author chenxi
 */
@Service
@Slf4j
public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    private static final DecimalFormat decimalFormat = new DecimalFormat("###################.###########");


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


    /**
     * 把表格转成List<>
     * @param wb
     * @param clz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> getListByXssf(XSSFWorkbook wb, Class<T> clz) throws IllegalAccessException, InstantiationException {
        XSSFRow row;
        XSSFSheet sheet;
        List<T> dataList = new ArrayList<>();
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            sheet = wb.getSheetAt(i);
            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                row = sheet.getRow(j);
                dataList.add(getObjectByXssfRow(row,clz));
            }
        }
        return dataList;
    }

    /**
     * 把每一行转成一个对象
     * @param row
     * @param clz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T getObjectByXssfRow(XSSFRow row, Class<T> clz) throws IllegalAccessException, InstantiationException {
        T object = clz.newInstance();
        Field[] beanFiled = clz.getDeclaredFields();
        for (int z = 0; z < row.getPhysicalNumberOfCells(); z++) {
            try {
                beanFiled[z].setAccessible(true);
                beanFiled[z].set(object, getStringByHss(row.getCell(z)));
            } catch (IllegalArgumentException e) {
                log.error("excel转对象error,", e);
            }
        }
        return object;
    }


    private static String getStringByHss(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            //数字会统一转成double类型,转成string需要去掉末尾.0
            return decimalFormat.format(cell.getNumericCellValue());
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else {
            return cell.getStringCellValue().trim();
        }
    }
}
