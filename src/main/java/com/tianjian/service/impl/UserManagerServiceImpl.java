package com.tianjian.service.impl;

import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.HotelRelationUserCurd;
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

import static com.tianjian.config.Constract.HOTELADMIN;
import static com.tianjian.config.Constract.MANAGER;

/**
 * Created by tianjian on 2018/12/31.
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    UserCurd userCurd;

    @Autowired
    HotelRelationUserManagerService hotelRelationUserManagerService;

    @Autowired
    HotelRelationUserCurd hotelRelationUserCurd;

    /**
     * 注册用户
     * @param userDO 用户模型数据
     */
    @Override
    public ServiceMessage<Boolean> registerUser(UserDO userDO) {

        if(checkAccountInfo(userDO.getAccount())) {
            return new ServiceMessage(ServiceEnum.DUPLICATION_NAME, null);
        }

        if(StringUtils.isBlank(userDO.getUserId())) {
            userDO.setUserId(UUIDUtil.getPreUUID("USER"));
        }
        userCurd.save(userDO);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }


    /**
     * 账号注销
     * @param userId 用户id
     */
    @Override
    public ServiceMessage<Boolean> unRegisterUser(String userId) {

        UserDO user = userCurd.findById(userId).get();

        if(MANAGER.equals(user.getRole())) {
            clearRelation(userId);
        }

        if(user == null) {
            return new ServiceMessage(ServiceEnum.NOT_FIND_NAME, null);
        }
        userCurd.delete(user);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Override
    public ServiceMessage<List<UserDO>> findUserDO(String role) {
        return new ServiceMessage<>(ServiceEnum.SUCCESS, userCurd.findByRole(role));
    }


    /**
     * 编辑管理用户信息
     * @param userManageModel 管理用户封装对象
     * @return 业务封装信息
     */
    @Override
    public ServiceMessage<UserDO> editManager(UserManageModel userManageModel) {
        ResponseData responseData = new ResponseData();
        String userID = userManageModel.getUserId();
        UserDO userDO = new UserDO();
        userDO.setRole(userManageModel.getRole());
        userDO.setUsername(userManageModel.getUsername());
        userDO.setAccount(userManageModel.getAccount());
        userDO.setPassword(userManageModel.getPassword());
        userDO.setEmail(userManageModel.getEmail());

        if(StringUtils.isBlank(userID)) {
            if(checkAccountInfo(userDO.getAccount())) {
                return new ServiceMessage(ServiceEnum.DUPLICATION_NAME, null);
            }
            userDO.setUserId(UUID.randomUUID().toString());
        } else {
            clearRelation(userID);
        }

        userDO = userCurd.save(userDO);


        String[] hotel = userManageModel.getHotel();

        if(hotel != null && hotel.length > 0) {
            for(String hotelId : hotel) {
                HotelRelationUser hotelRelationUser = new HotelRelationUser();
                hotelRelationUser.setHotelId(hotelId);
                hotelRelationUser.setUserId(userDO.getUserId());
                hotelRelationUserManagerService.saveHotelRelationUser(hotelRelationUser);
            }
        }

        return new ServiceMessage<>(ServiceEnum.SUCCESS, userDO);

    }

    /**
     * 清除用户房间关联关系
     */
    private void clearRelation(String userId) {
        List<HotelRelationUser> relations = hotelRelationUserCurd.findByUserId(userId);
        if(relations != null && relations.size() > 0 ) {
            hotelRelationUserCurd.deleteByUserId(userId);
        }
    }

    /**
     * 检查账号是否重复
     * @param account
     * @return
     */
    private boolean checkAccountInfo(String account) {
        UserDO user = userCurd.findByAccount(account);
        if(user != null) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}