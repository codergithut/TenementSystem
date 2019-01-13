package com.tianjian.model;

/**
 * 用户管理model
 * Created by tianjian on 2019/1/12.
 */
public class UserManageModel {

    /**
     * 数据ID
     */
    private String id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户管理id列表
     */
    private String[] hotel;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户角色
     */
    private String role;

    //set get...
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getHotel() {
        return hotel;
    }

    public void setHotel(String[] hotel) {
        this.hotel = hotel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

