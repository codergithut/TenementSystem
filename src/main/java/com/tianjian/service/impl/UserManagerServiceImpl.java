package com.tianjian.service.impl;

import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.UserCurd;
import com.tianjian.service.ServiceEnum;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.UserManagerService;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianjian on 2018/12/31.
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    UserCurd userCurd;
    /**
     * 注册用户
     * @param userDO
     */
    @Override
    public ServiceMessage<Boolean> registerUser(UserDO userDO) throws Exception {
        UserDO user = userCurd.findByAccount(userDO.getAccount());
        if(user != null) {
            return new ServiceMessage(ServiceEnum.DUPLICATION_NAME, null);
        }
        if(StringUtils.isBlank(userDO.getUserId())) {
            userDO.setUserId(UUIDUtil.getPreUUID("USER"));
        }
        userCurd.save(userDO);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }


    /**
     * 取消注册
     * @param userDO
     */
    @Override
    public ServiceMessage<Boolean> unRegisterUser(UserDO userDO) {

        UserDO user = userCurd.findByAccount(userDO.getAccount());
        if(user == null) {
            return new ServiceMessage(ServiceEnum.NOT_FIND_NAME, null);
        }
        userCurd.delete(userDO);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public ServiceMessage<List<UserDO>> findUserDO() {
        return new ServiceMessage<>(ServiceEnum.SUCCESS, userCurd.findAll());
    }
}
