/*
 * 文件：Order.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年09月10日 17:30:18
 * 上次修改时间：2019年09月10日 17:30:18
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.test.sdk.toolsoso.marqueeview.bean;

public class Order {

    public static int STATUS_CANCEL = -1;//订单取消
    public static int STATUS_COMPLETE = 0;//订单完成

    public static int STATUS_WAITPAY = 1;//待支付
    public static int STATUS_WAITSEND = 2;//待发货
    public static int STATUS_INTRANSIT = 3;//运输中
    public static int STATUS_WAITTAKE = 4;//待取货
    public static int STATUS_WAITAPPRAISE = 5;//待评价
    public static int STATUS_WAITBACK = 6;//待商家退款
    public static int STATUS_ONBACK = 7;//已退款

    long orderid;//订单id
    int status;//订单状态
    String newest_date;//日期
    String newest_message;//最新信息
    String picUrl;//实物图

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNewest_date() {
        return newest_date;
    }

    public void setNewest_date(String newest_date) {
        this.newest_date = newest_date;
    }

    public String getNewest_message() {
        return newest_message;
    }

    public void setNewest_message(String newest_message) {
        this.newest_message = newest_message;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
