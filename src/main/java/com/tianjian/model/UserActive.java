package com.tianjian.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by tianjian on 2019/2/16.
 */
public class UserActive {

    private String account;

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public static void main(String[] args) {
        UserActive userActive = new UserActive();
        userActive.setCode("userID");
        userActive.setAccount("test");
        System.out.println(JSON.toJSONString(userActive));
    }
}
