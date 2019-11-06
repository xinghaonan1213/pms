package com.xhn.test;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppTest01 {

    @Test
    public void test02(){
        try {
            //获取Excel
            Workbook wb = WorkbookFactory.create(new File("D:\\Desktop\\book01.xls"));
            //获取单元格
            Sheet sheet = wb.getSheetAt(0);
            //获取单元格下的数据
            System.out.println("第一行的行号是："+sheet.getFirstRowNum());
            System.out.println("末行的行号是："+sheet.getLastRowNum());
            for (int i = sheet.getFirstRowNum(); i <sheet.getLastRowNum() ; i++) {
                //获取行号
                Row row = sheet.getRow(i);
                //获取每行的数据对应的列
                //遍历每一行下的每一列数据
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    //获取行对应的列
                    Cell cell = row.getCell(j);
                   String value= parseExcelValueToString(cell);
                   if (i>0&&j==0){
                       value=value.substring(0,value.indexOf("."));
                   }
                    System.out.print(value+"  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("出异常了");
            e.printStackTrace();
        }
    }

    private String parseExcelValueToString(Cell cell) {
        String o = null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        switch(cell.getCellType()){
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                o = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    o = sdf.format(date);
                }else {
                    BigDecimal decimal=new BigDecimal(String.valueOf(cell.getNumericCellValue()));
                    o = decimal.toPlainString();
                }
                break;
            case Cell.CELL_TYPE_STRING:
                o = cell.getStringCellValue();
                break;
            default:
                o = null;
                break;
        }
        return o;
    }

    @Test
    public void test01()  {
        //创建Excel
        Workbook wb = new XSSFWorkbook();
        //创建单元格
        Sheet sheet1 = wb.createSheet();
        Sheet sheet2 = wb.createSheet();
        Sheet sheet3 = wb.createSheet();

        //创建第一行
        Row row = sheet1.createRow(0);
        //创建列
        Cell cell11 = row.createCell(0);
        Cell cell12 = row.createCell(1);
        Cell cell13 = row.createCell(2);

        //列里边设置值
        cell11.setCellValue("id");
        cell12.setCellValue("序号");
        cell13.setCellValue("联系人");
        FileOutputStream fos=null;
        try  {
            fos = new FileOutputStream("D:\\Desktop\\workbook.xls");
            wb.write(fos);
        }catch (IOException ex){
              ex.getMessage();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
