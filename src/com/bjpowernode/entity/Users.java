package com.bjpowernode.entity;

/**
 * @program: untitledJDBC
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-09 15:00:42
 **/
public class Users {
    private Integer userId;
    private String userName;
    private String password;
     private String sex;
    private String email;

    public Users(int userId, String userName, String password, String sex, String email) {
    }

    public Users(Integer userId, String userName, String password, String sex, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}