package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.exportMethod.ExcelUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args)  {



        System.out.println("Hello world!");

        Cat cat = new Cat("张三",12);
        Cat cat1 = new Cat("李四",13);
        Cat cat2 = new Cat("王五",12);
        Cat cat3 = new Cat("张六",18);
        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(cat);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        ExcelUtils.exportSheet(cats);







    }
}