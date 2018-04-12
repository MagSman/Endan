package com.my.xxx.endan.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by sjh on 2018/4/12.
 * 250135506@qq.com
 */

public class Person extends BmobObject {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
