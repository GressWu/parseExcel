package org.example.entity;

import org.example.annotation.ExportField;

public class Gift {

    @ExportField(name = "名字")
    public  String name;
    @ExportField(name = "use")
    public  String user;

    public Gift() {
    }

    public Gift(String name, String user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
