package com.tianjian.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by tianjian on 2019/2/16.
 */
public class UserActive {

    private String userId;

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static void main(String[] args) {
        UserActive userActive = new UserActive();
        userActive.setCode("userID");
        System.out.println(JSON.toJSONString(userActive));
    }
}
