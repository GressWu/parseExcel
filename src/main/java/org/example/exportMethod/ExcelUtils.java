package org.example.exportMethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.annotation.ExportField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

    /**
     * 导出对象中上有自定义注解的字段
     * 支持一个子列表
     *
     * @param list
     */
    public static void exportSheet(List<?> list) {


        Workbook wb = new XSSFWorkbook();
        //实体类包含的field
        Field[] fields = list.get(0).getClass().getDeclaredFields();

        //创建sheet页
        Sheet sheet1 = createSheet(wb, "sheet1");
        //创建表头
        createHeader(fields, sheet1);
        //写入数据
        writeDataRecursion(fields, sheet1,  list,false,1,0);
        //输出Excel
        try (OutputStream fileOut = new FileOutputStream("C:\\Users\\11629\\Desktop\\testExcel.xlsx")) {
            wb.write(fileOut);
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建表头
     *
     * @param fields
     * @param sheet
     */
    private static void createHeader(Field[] fields, Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        int colNum = 0;
        for (Field field : fields) {
            ExportField exportField = field.getAnnotation(ExportField.class);
            if (exportField != null) {
                Cell cell = headerRow.createCell(colNum++);
                cell.setCellValue(exportField.name());
            }
        }
    }

    /**
     * 创建Sheet页
     *
     * @param workbook
     * @param sheetName
     * @return
     */
    private static Sheet createSheet(Workbook workbook, String sheetName) {
        return workbook.createSheet(sheetName);
    }

    /**
     * 写入数据
     *
     * @param fields
     * @param sheet
     * @param rowNum
     * @param list
     */
    private static void writeData(Field[] fields, Sheet sheet, int rowNum, List list) {
        for (Object object : list) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Field field : fields) {
                ExportField exportField = field.getAnnotation(ExportField.class);
                if (exportField != null) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(object);
                        Cell cell = row.createCell(colNum++);
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof List) {
                            List subList = (ArrayList) value;
                            cell.setCellValue(subList.get(0).toString());
                            for (int t = 1; t < subList.size(); t++) {
                                Row subRow = sheet.createRow(rowNum++);
                                Cell subCell = subRow.createCell(colNum - 1);
                                subCell.setCellValue(subList.get(t).toString());
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 递归生成包含List对象的列表 但是List的子对象必须也有注解
     * @param fields
     * @param sheet
     * @param list
     * @param subListFlag
     * @param rowNum
     * @param colNum
     * @return
     */
    private static int writeDataRecursion(Field[] fields, Sheet sheet,  List list,boolean subListFlag,int rowNum,int colNum) {
        int flagValue = colNum;
        for (Object object : list) {
            Row row = sheet.createRow(rowNum++);

            colNum = flagValue;
            if(!subListFlag){
                colNum = 0;
            }

            for (Field field : fields) {
                ExportField exportField = field.getAnnotation(ExportField.class);
                if (exportField != null) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(object);
                        if (value instanceof List) {
                            List subList = (ArrayList)value;
                            if(subList.size()>0&&subList!=null){
                                Field[] subfields = subList.get(0).getClass().getDeclaredFields();
                                rowNum = writeDataRecursion(subfields, sheet,  subList,true,rowNum,colNum);
                            }
                        } else {
                            Cell cell = row.createCell(colNum++);
                            System.out.println("行数："+rowNum+" 列数："+colNum +" 值:"+value);
                            if (value instanceof String) {
                                cell.setCellValue((String) value);
                            } else if (value instanceof Integer) {
                                cell.setCellValue((Integer) value);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        logger.info(e);
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return rowNum;
    }
}
