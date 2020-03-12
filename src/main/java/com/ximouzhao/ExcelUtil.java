package com.ximouzhao;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @created by wjf
 * @date 2019/12/10 14:16
 * @description:
 */
@Component
public class ExcelUtil {

    public static String getCellStringValue(Cell cell, int column) {
        if (cell == null) {
            return null;
        } else {
            return cell.getStringCellValue();
        }
    }

    /**
     * 将字符类型转换未单元格日期，主要作用是防止空指针
     */
    public static Date parseCellStringValueToDate(Row row, int column) {
        String dateStr=ExcelUtil.getCellStringValue(row, column);
        if(StringUtil.isEmpty(dateStr)){
            throw new RuntimeException("获取日期失败：第" + (row.getRowNum() + 1)
                    + "行，第" + ExcelUtil.convertIndexToExcelCol(column + 1)+"列");
        }
        try{
            return DateTimeUtil.defaultFormatDate(dateStr);
        }catch (Exception e){
            throw new RuntimeException("格式化日期失败：第" + (row.getRowNum() + 1)
                    + "行，第" + ExcelUtil.convertIndexToExcelCol(column + 1)+"列，值："
                    +dateStr+",格式：yyyy-MM-dd");
        }
    }
    /**
     * 获取日期类型单元格的数值
     */
    public static Date getCellDateValue(Row row, int column) {
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(column);
        if (cell == null) {
            return null;
        } else {
            CellType cellTypeEnum = cell.getCellTypeEnum();
            switch (cellTypeEnum){
                case NUMERIC:
                    return cell.getDateCellValue();
                case BLANK:
                    return null;
                case STRING:
                case FORMULA:
                    return ExcelUtil.parseCellStringValueToDate(row,column);
                default:
                    throw new RuntimeException("单元格格式不正确，应为日期类型：第" + (row.getRowNum() + 1)
                            + "行，第" + ExcelUtil.convertIndexToExcelCol(column + 1)+"列");
            }
        }
    }
    /**
     * 获取日期类型单元格的数值
     */
    public static BigDecimal getCellDecimalValue(Row row, int column) {
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(column);
        if (cell == null) {
            return null;
        } else {
            CellType cellTypeEnum = cell.getCellTypeEnum();
            switch (cellTypeEnum){
                case NUMERIC:
                    return BigDecimal.valueOf(cell.getNumericCellValue());
                case STRING:
                case BLANK:
                    return BigDecimal.ZERO;
                case FORMULA:
                    return BigDecimal.valueOf(cell.getNumericCellValue());
                default:
                    throw new RuntimeException("单元格格式不正确，应为数值类型：第" + (row.getRowNum() + 1)
                            + "行，第" + ExcelUtil.convertIndexToExcelCol(column + 1)+"列");
            }
        }
    }
    /**
     * 获取单元格字符串的值，主要作用是防止空指针
     *
     * @param row
     * @param column
     * @return
     */
    public static String getCellStringValue(Row row, int column) {
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(column);
        if (cell == null) {
            return null;
        } else {
            CellType cellTypeEnum = cell.getCellTypeEnum();
            switch (cellTypeEnum){
                case STRING:
                    return cell.getStringCellValue();
                case BLANK:
                    return null;
                case FORMULA:
                    return cell.getNumericCellValue()+"";
                default:
                    throw new RuntimeException("单元格格式不正确，应为字符类型：第" + (row.getRowNum() + 1)
                            + "行，第" + ExcelUtil.convertIndexToExcelCol(column + 1)+"列");
            }
        }
    }

    /**
     * get字符串类型单元格值，并尝试转换为bigDecimal
     *
     * @param row
     * @param column
     * @return
     */
    public static BigDecimal parseCellStringValueToDecimal(Row row, int column) {
        if (row == null) {
            return null;
        }
        String strValue=ExcelUtil.getCellStringValue(row,column);
        if(StringUtil.isEmpty(strValue)){
            return null;
        }
        try {
            return new BigDecimal(strValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException("将字符串转换为数字失败,行：" + (row.getRowNum() + 1) + " 行："
                    + ExcelUtil.convertIndexToExcelCol(column + 1)+"列");
        }
    }

    /**
     * 该方法用来将具体的数据转换成Excel中的ABCD列
     *
     * @param columnIndex：需要转换成字母的数字
     * @return column:ABCD列名称
     **/
    public static String convertIndexToExcelCol(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }

    /**
     * 26进制转10进制
     *
     * @param
     * @return
     */
    public static int counvertExcelColToIndex(String string) {
        char[] charArr = string.toCharArray();
        int res = 0;
        int exp = 0;
        for (int i = charArr.length - 1; i >= 0; i--) {
            int num = charArr[i] - 'A' + 1;
            if (num > 26 || num < 1) {
                throw new RuntimeException("将26进制转换为10进制输入不合法：" + string);
            }
            res = (int) (res + num * Math.pow(26, exp));
            exp++;
        }
        return res;
    }

}
