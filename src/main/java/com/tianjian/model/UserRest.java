package com.tianjian.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by tianjian on 2019/2/16.
 */
public class UserRest {

    private String userId;

    private String password;

    private String code;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        UserRest userRest = new UserRest();
        userRest.setUserId("userID");
        userRest.setPassword("haha");
        userRest.setCode("5b4ea76e-db3e-4184-a0d1-564970ca4dfb");
        System.out.println(JSON.toJSONString(userRest));
    }
}
