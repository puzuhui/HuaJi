package com.mingxuan.huaji.layout.news.bean;

/**
 * Created by Admin on 2018/5/16.
 * 公司：铭轩科技
 */

public class NotificationModel {
    String titile;
    String message;
    String time;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
