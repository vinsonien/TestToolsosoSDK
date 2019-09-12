/*
 * 文件：Merchandise.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年09月03日 17:12:39
 * 上次修改时间：2019年09月03日 17:12:38
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.test.sdk.toolsoso.marqueeview.bean;

import java.util.List;

public class Merchandise {


    String name;
    String picurl;
    long price;
    List<String> tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
