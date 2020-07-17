package com.example.postapi.model;

import java.util.Date;

public class Data{
    private String userid;
    private String user_lastname;
    private int user_hold_point;
    private Date user_last_visit_datetime;



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public int getUser_hold_point() {
        return user_hold_point;
    }

    public void setUser_hold_point(int user_hold_point) {
        this.user_hold_point = user_hold_point;
    }

    public Date getUser_last_visit_datetime() {
        return user_last_visit_datetime;
    }

    public void setUser_last_visit_datetime(Date user_last_visit_datetime) {
        this.user_last_visit_datetime = user_last_visit_datetime;
    }

    @Override
    public String toString() {
        return "Response{" +
                "userid='" + userid + '\'' +
                ", user_lastname='" + user_lastname + '\'' +
                ", user_hold_point=" + user_hold_point +
                ", user_last_visit_datetime=" + user_last_visit_datetime +
                '}';
    }
}
