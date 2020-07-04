package com.example.postapi.model;

import java.util.Date;

public class Response {
    private String userid;
    private String username;
    private int point;
    private Date last_visit_datetime;

    public Response(String userid, String username, int point, Date last_visit_datetime) {
        this.userid = userid;
        this.username = username;
        this.point = point;
        this.last_visit_datetime = last_visit_datetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getLast_visit_datetime() {
        return last_visit_datetime;
    }

    public void setLast_visit_datetime(Date last_visit_datetime) {
        this.last_visit_datetime = last_visit_datetime;
    }

    @Override
    public String toString() {
        return "Response{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", point=" + point +
                ", last_visit_datetime=" + last_visit_datetime +
                '}';
    }
}
