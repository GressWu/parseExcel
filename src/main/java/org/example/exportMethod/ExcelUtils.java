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
import java.util.List;

public class ExcelUtils {

    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

    public static void exportSheet(List<?> list){


        Workbook wb = new XSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("C:\\Users\\11629\\Desktop\\testExcel.xlsx")) {

            // 写入表头
            Sheet sheet1 = wb.createSheet("Sheet1");
            Row headerRow = sheet1.createRow(0);
            Field[] fields = list.get(0).getClass().getDeclaredFields();
            int colNum = 0;
            for (Field field : fields) {
                ExportField exportField = field.getAnnotation(ExportField.class);
                if (exportField != null) {
                    Cell cell = headerRow.createCell(colNum++);
                    cell.setCellValue(exportField.name());
                }
            }


            // 写入数据
            int rowNum = 1;
            for (Object object : list) {
                Row row = sheet1.createRow(rowNum++);
                colNum = 0;
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
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            wb.write(fileOut);
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
