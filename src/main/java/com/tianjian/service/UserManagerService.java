package com.tianjian.service;

import com.tianjian.data.bean.UserDO;
import com.tianjian.model.ServiceMessage;

import java.util.List;

/**
 * Created by tianjian on 2018/12/31.
 */
public interface UserManagerService {

    /**
     * 注册用户
     * @param userDO
     */
    ServiceMessage<Boolean> registerUser(UserDO userDO);



    /**
     * 取消注册
     * @param userDO
     */
    ServiceMessage<Boolean> unRegisterUser(UserDO userDO);

    /**
     * 查询所有用户
     * @return
     */
    ServiceMessage<List<UserDO>> findUserDO();

}
