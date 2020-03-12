package com.ximouzhao;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class CalExcel  {
    private static String fileName="C:\\Users\\JBS\\Desktop\\2020开票\\西安沐易家居用品有限公司12月开票失败和未开票订单limit40000.xls";

    public static void main(String[] args) throws Exception {
        File file =new File(fileName);
        String fileName = file.getName();
        Workbook workbook;
        if (fileName.endsWith(".xlsx")) {
            try (InputStream inputStream = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException ioe) {
                throw new Exception("文件操作异常", ioe);
            }
        } else if (fileName.endsWith(".xls")) {
            try (InputStream inputStream =  new FileInputStream(file)) {
                workbook = new HSSFWorkbook(inputStream);
            } catch (IOException ioe) {
                throw new Exception("文件操作异常", ioe);
            }
        } else {
            throw new Exception("仅允许上传后缀名为.xls或者.xlsx的excel表格");
        }
        Sheet sheet = workbook.getSheetAt(0);
        BigDecimal a=BigDecimal.ZERO;
        BigDecimal b=new BigDecimal("5000000");
        int startRowNum=2;
        int colNum=13;
        int rowNum=startRowNum-1;
        while(a.compareTo(b)<0){
            Row row=sheet.getRow(rowNum);
            if(row==null||row.getCell(colNum)==null){
                throw new Exception("订单数不够，开始行："+startRowNum+"  最终行："+rowNum);
            }
            a=a.add(ExcelUtil.getCellDecimalValue(row,colNum));
            System.out.println(ExcelUtil.getCellStringValue(row,1));
            rowNum++;
        }
        System.out.println("文件名:"+fileName+" 起始行："+startRowNum+"结尾行数："+rowNum+"合计数："+a+" 订单数："+(rowNum-startRowNum+1));
    }
}
