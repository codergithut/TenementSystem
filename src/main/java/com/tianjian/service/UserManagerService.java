package com.tianjian.service;

import com.tianjian.data.bean.UserDO;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.UserManageModel;

import java.util.List;

/**
 * Created by tianjian on 2018/12/31.
 */
public interface UserManagerService {

    /**
     * 注册用户
     * @param userDO
     */
    ServiceMessage<Boolean> registerUser(UserDO userDO) throws Exception;



    /**
     * 取消注册
     * @param userId
     */
    ServiceMessage<Boolean> unRegisterUser(String userId);

    /**
     * 查询所有用户
     * @return
     */
    ServiceMessage<List<UserDO>> findUserDO();

    /**
     *
     * @param userManageModel
     * @return
     */
    ServiceMessage editManager(UserManageModel userManageModel);

}
