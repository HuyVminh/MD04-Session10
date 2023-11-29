package com.ra.model.entity;

import java.util.Date;

public class Student {
    private int id;
    private String userName;
    private String email;
    private String address;
    private java.sql.Date birthday;

    public Student() {
    }

    public Student(int id, String userName, String email, String address, java.sql.Date birthday) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }
}
