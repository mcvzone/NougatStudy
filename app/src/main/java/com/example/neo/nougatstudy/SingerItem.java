package com.example.neo.nougatstudy;

/**
 * Created by neo on 2017-07-17.
 */

public class SingerItem {
    private String name;
    private String mobile;
    private int id;

    public SingerItem(String name, String mobile, int id) {
        this.name = name;
        this.mobile = mobile;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
