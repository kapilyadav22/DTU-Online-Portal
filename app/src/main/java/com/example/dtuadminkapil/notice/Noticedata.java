package com.example.dtuadminkapil.notice;

public class Noticedata {
    String title,image,date,time,key;

    public Noticedata() {
    }

    public Noticedata(String title, String image, String date, String time, String key) {
        this.title = title;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getKey() {
        return key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
