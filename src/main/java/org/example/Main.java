package org.example;

import org.example.entity.Cat;
import org.example.entity.Gift;
import org.example.exportMethod.ExcelUtils;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {


        System.out.println("Hello world!");
        Cat cat = new Cat("张三", 12);
        Cat cat1 = new Cat("李四", 13);
        Cat cat2 = new Cat("王五", 12);
        Cat cat3 = new Cat("张六", 18);
        ArrayList<Gift> strings = new ArrayList<>();
        Gift gift = new Gift("asd", "haha");
        Gift gift1 = new Gift("asd1", "haha1");
        strings.add(gift1);
        strings.add(gift);

        ArrayList<Cat> cats = new ArrayList<>();
        cat.setLists(strings);
        cats.add(cat);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        ExcelUtils.exportSheet(cats);

    }
}