package org.example;

import org.example.annotation.ExportField;

public class Cat {
    @ExportField(name = "姓名")
    public String name;
    @ExportField(name = "年龄")
    public int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat() {
    }
}
