package com.example.postapi.model;

public class Response {
    private String userid;
    private String username;
    private int point;

    public Response(String userid, String username, int point) {
        this.userid = userid;
        this.username = username;
        this.point = point;
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

    @Override
    public String toString() {
        return "Response{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", point=" + point +
                '}';
    }
}
