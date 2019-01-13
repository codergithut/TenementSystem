package com.tianjian.service.impl;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.RoomDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.HotelCurd;
import com.tianjian.data.service.HotelRelationUserCurd;
import com.tianjian.data.service.RoomCurd;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
@Service
public class HotelManagerServiceImpl implements HotelManagerService {

    @Autowired
    HotelCurd hotelCurd;

    @Autowired
    HotelRelationUserCurd hotelRelationUserCurd;

    @Autowired
    RoomCurd roomCurd;

    @Autowired
    UserCurd userCurd;

    @Override
    public ServiceMessage<Page<HotelDO>> findHotelDO(String userId, Pageable pageable) {
        UserDO userDO = userCurd.findById(userId).get();
        List<HotelDO> datas = new ArrayList<>();
        if(userDO == null) {
            return new ServiceMessage<>(ServiceEnum.NOT_FIND_NAME, null);
        }

        if("USER".equals(userDO.getRole())) {
            return new ServiceMessage<Page<HotelDO>>(ServiceEnum.SUCCESS, hotelCurd.findAll(pageable));
        } else {
            List<String> hotelIds = new ArrayList<String>();
            List<HotelRelationUser> relationUsers = hotelRelationUserCurd.findByUserId(userId);
            if(relationUsers != null && relationUsers.size() >0) {
                relationUsers.forEach(t -> {
                    hotelIds.add(t.getHotelId());
                });
                return new ServiceMessage<Page<HotelDO>>(ServiceEnum.SUCCESS, hotelCurd.getHotelByIds(hotelIds, pageable));
            }
            return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
        }

    }

    @Override
    public ServiceMessage<HotelDO> saveHotelDO(HotelDO hotelDO) throws Exception {
        if(StringUtils.isBlank(hotelDO.getHotelId())) {
            hotelDO.setHotelId(UUIDUtil.getPreUUID("HOTEL"));
        }
        HotelDO save = hotelCurd.save(hotelDO);
        if(save != null) {
            return new ServiceMessage(ServiceEnum.SUCCESS,  save);
        } else {
            return new ServiceMessage(ServiceEnum.SAVE_NULL,  null);
        }

    }

    @Override
    public ServiceMessage deleteHotelDO(String hotelId) {
        hotelCurd.deleteById(hotelId);
        return new ServiceMessage(ServiceEnum.SUCCESS,  null);
    }

    @Override
    public ServiceMessage<HotelDetail> getHotelDetail(String hotelId) {
        HotelDetail hotelDetail = new HotelDetail();
        HotelDO hotelDO = hotelCurd.findById(hotelId).get();
        List<RoomDO> rooms = roomCurd.findByHotelId(hotelId);
        hotelDetail.setHotelInfo(hotelDO);
        hotelDetail.setRoomsInfo(rooms);
        return new ServiceMessage(ServiceEnum.SUCCESS,  hotelDetail);
    }

}
