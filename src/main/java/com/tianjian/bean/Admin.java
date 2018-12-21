package com.tianjian.bean;

/**
 * Created by tianjian on 2018/11/29.
 */
public class Admin {

    /**
     * 管理员ID
     */
    private String adminId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;


    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
