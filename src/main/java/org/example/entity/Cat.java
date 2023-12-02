package org.example.entity;

import org.example.annotation.ExportField;

import java.util.List;

public class Cat {
    @ExportField(name = "姓名")
    public String name;
    @ExportField(name = "年龄")
    public int age;

    @ExportField(name = "列表")
    public List<Gift> lists;

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

    public Cat(String name, int age, List<Gift> lists) {
        this.name = name;
        this.age = age;
        this.lists = lists;
    }

    public List<Gift> getLists() {
        return lists;
    }

    public void setLists(List<Gift> lists) {
        this.lists = lists;
    }
}
