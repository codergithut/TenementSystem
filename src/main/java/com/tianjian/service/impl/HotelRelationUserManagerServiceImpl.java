package com.tianjian.service.impl;

import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.HotelRelationUserCurd;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.HotelRelationUserManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 酒店用户关系业务类
 * Created by tianjian on 2019/1/1.
 */
@Service
public class HotelRelationUserManagerServiceImpl implements HotelRelationUserManagerService {

    @Autowired
    private HotelRelationUserCurd hotelRelationUserCurd;

    @Autowired
    UserCurd userCurd;

    /**
     * 根据用户ID查找关系记录
     * @param userID 用户id
     * @return 关系记录
     */
    @Override
    public ServiceMessage<List<HotelRelationUser>> findHotelIDsByUserId(String userID) {
        UserDO userDO = userCurd.findById(userID).get();
        if(userDO == null) {
            return new ServiceMessage<>(ServiceEnum.NOT_FIND_NAME, null);
        }
        List<HotelRelationUser> hotelRelationUsers = hotelRelationUserCurd.findByUserId(userID);
        return new ServiceMessage<List<HotelRelationUser>>(ServiceEnum.SUCCESS, hotelRelationUsers);
    }

    /**
     * 添加酒店管理员关系记录
     * @param hotelRelationUser 关系实体
     * @return 业务封装对象
     */
    @Override
    public ServiceMessage<HotelRelationUser> saveHotelRelationUser(HotelRelationUser hotelRelationUser) {
        if(StringUtils.isBlank(hotelRelationUser.getRelationId())) {
            hotelRelationUser.setRelationId(UUIDUtil.getPreUUID("RELATION"));
        }
        HotelRelationUser data = hotelRelationUserCurd.save(hotelRelationUser);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, data);
    }

    /**
     * 删除关系对象
     * @param relationId 关系数据ID
     * @return 业务数据封装
     */
    @Override
    public ServiceMessage deleteHotelRelationUser(String relationId) {
        Optional<HotelRelationUser> data = hotelRelationUserCurd.findById(relationId);
        if(!data.isPresent()) {
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }
        hotelRelationUserCurd.deleteById(relationId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }


}
