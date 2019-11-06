package com.xhn.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {
    public static String parseExcelValueToString(Cell cell) {
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
}
