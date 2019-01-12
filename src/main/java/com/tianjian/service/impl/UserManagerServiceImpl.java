package com.tianjian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.UserManageModel;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.HotelRelationUserManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.UserManagerService;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2018/12/31.
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    UserCurd userCurd;

    @Autowired
    HotelRelationUserManagerService hotelRelationUserManagerService;
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
     * @param userId
     */
    @Override
    public ServiceMessage<Boolean> unRegisterUser(String userId) {

        UserDO user = userCurd.findById(userId).get();
        if(user == null) {
            return new ServiceMessage(ServiceEnum.NOT_FIND_NAME, null);
        }
        if("MANAGER".equals(user.getRole())) {
            //todo 清理关联关系，和酒店的关联关系
        }
        userCurd.delete(user);
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


    @Override
    public ServiceMessage editManager(UserManageModel userManageModel) {
        ResponseData responseData = new ResponseData();
        String userID = userManageModel.getId();
        UserDO userDO = new UserDO();
        userDO.setRole("MANAGER");
        userDO.setUsername(userManageModel.getUsername());
        userDO.setAccount(userManageModel.getAccount());
        userDO.setPassword(userManageModel.getPassword());
        userDO.setEmail(userManageModel.getEmail());

        if(userID != null && StringUtils.isNoneBlank(userID)) {
            userDO.setUserId(userManageModel.getId());
            //todo 清除已有的关联关系
        } else {
            userDO.setUserId(UUID.randomUUID().toString());
            //todo 验证是否用户账号重复
        }
        userDO = userCurd.save(userDO);


        String[] hotel = userManageModel.getHotel();

        for(String hotelId : hotel) {
            HotelRelationUser hotelRelationUser = new HotelRelationUser();
            hotelRelationUser.setHotelId(hotelId);
            hotelRelationUser.setUserId(userDO.getUserId());
            hotelRelationUserManagerService.saveHotelRelationUser(hotelRelationUser);
        }
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);

    }

    public static void main(String[] args) {
        UserManageModel userManageModel = new UserManageModel();
        userManageModel.setEmail("1332323@qq.com");
        userManageModel.setPassword("11223");
        userManageModel.setAccount("tianjian3332232");
        userManageModel.setHotel(new String[]{"111111", "222222", "33333"});
        userManageModel.setId(UUID.randomUUID().toString());
        userManageModel.setUsername("sfsdfsd");
        System.out.println(JSONObject.toJSONString(userManageModel));
    }
}