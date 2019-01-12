package com.tianjian.service.impl;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.service.HotelRelationUserCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.HotelRelationUserManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
@Service
public class HotelRelationUserManagerServiceImpl implements HotelRelationUserManagerService {

    @Autowired
    private HotelRelationUserCurd hotelRelationUserCurd;


    @Override
    public ServiceMessage<List<HotelRelationUser>> findHotelIDsByUserId(String userID) {
        List<HotelRelationUser> hotelRelationUsers = hotelRelationUserCurd.findByUserId(userID);
        return new ServiceMessage<List<HotelRelationUser>>(ServiceEnum.SUCCESS, hotelRelationUsers);
    }

    @Override
    public ServiceMessage<HotelRelationUser> saveHotelRelationUser(HotelRelationUser hotelRelationUser) {
        if(StringUtils.isBlank(hotelRelationUser.getRelationId())) {
            try {
                hotelRelationUser.setRelationId(UUIDUtil.getPreUUID("RELATION"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HotelRelationUser data = hotelRelationUserCurd.save(hotelRelationUser);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, data);
    }

    @Override
    public ServiceMessage deleteHotelRelationUser(String relationId) {
        hotelRelationUserCurd.deleteById(relationId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }


}
