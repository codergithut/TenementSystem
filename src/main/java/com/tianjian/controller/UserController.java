package com.tianjian.controller;

import com.tianjian.data.impl.UserDao;
import com.tianjian.model.bean.UserDO;
import com.tianjian.model.front.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/register")
    public ResponseData<String> registerUser(UserDO userDO) {
        ResponseData<String> responseData = new ResponseData<>();
        userDO.setId(UUID.randomUUID().toString());
        UserDO userDo = userDao.save(userDO);
        if(userDo != null) {
            return responseData.buidSuccessResponseData("register user success", null);
        } else {
            return responseData.buildFailResponseData("regisiter user fail", 0);
        }
    }
}
