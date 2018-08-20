package com.example.galv.exame.entities;

import java.io.Serializable;

public class Tag implements Serializable {

    private int code;
    private String name;

    public Tag(){
        this.code = 0;
        this.name = "";
    }

    public Tag(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
