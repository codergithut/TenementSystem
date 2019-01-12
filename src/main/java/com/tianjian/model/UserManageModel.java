package com.tianjian.model;

/**
 * Created by tianjian on 2019/1/12.
 */
public class UserManageModel {

    private String id;

    private String account;

    private String password;

    private String username;

    private String[] hotel;

    private String email;

    private String role;

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

