/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.google.common.collect.Lists;
/**
 * TODO: description
 * Date: 2019-08-16
 *
 * @author zhaqianming
 */
public class PoiUtils {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    public static List<List<String>> readExcel(String fileName) throws IOException {
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(fileName);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<List<String>> list = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    List<String> cells = Lists.newArrayList();
//                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells.add(getCellValue(cell));
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }

    private static Workbook getWorkBook(String fileName) throws IOException {
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            throw new IOException(fileName + "不是excel文件");
        }
        Workbook workbook = null;
        InputStream is = PoiUtils.class.getResourceAsStream(fileName);
        if (fileName.endsWith(xls)) {
            //2003
            workbook = new HSSFWorkbook(is);
        } else if (fileName.endsWith(xlsx)) {
            //2007
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    Date date = cell.getDateCellValue();
                    cellValue = DateUtils.formatDateTime(date);
                } else {
                    // 去除小数点
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cellValue = String.valueOf(cell.getStringCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "";
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }
}
