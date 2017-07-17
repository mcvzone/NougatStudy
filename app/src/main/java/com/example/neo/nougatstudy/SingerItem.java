package com.example.neo.nougatstudy;

/**
 * Created by neo on 2017-07-17.
 */

public class SingerItem {
    private String name;
    private String mobile;

    public SingerItem(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
