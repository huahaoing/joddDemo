package com.ctt.jdoo.demo.bean.vo;

public class User {
    private Integer id;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
