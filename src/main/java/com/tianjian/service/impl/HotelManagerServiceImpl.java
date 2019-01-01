package com.tianjian.service.impl;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.service.HotelCurd;
import com.tianjian.data.service.HotelRelationUserCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.ServiceEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ServiceMessage<List<HotelDO>> findHotelDO(HotelDO hotelDO) {
        List<HotelDO> datas = new ArrayList<>();
        if(hotelDO != null && StringUtils.isNoneBlank(hotelDO.getHotelId())) {
            datas.add(hotelCurd.findById(hotelDO.getHotelId()).get());
        } else {
            datas = hotelCurd.findAll();
        }

        if(datas.size() > 0) {
            return new ServiceMessage(ServiceEnum.SUCCESS, datas);
        } else {
            return new ServiceMessage(ServiceEnum.SEARCH_NULL, null);
        }

    }

    @Override
    public ServiceMessage<HotelDO> saveHotelDO(HotelDO hotelDO) {
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
    public ServiceMessage<List<HotelDO>> getHotelByUserIds(String userId) {
        List<String> hotelIds = new ArrayList<String>();
        List<HotelRelationUser> relationUsers = hotelRelationUserCurd.findByUserId(userId);
        if(relationUsers != null && relationUsers.size() >0) {
            relationUsers.forEach(t -> {
                hotelIds.add(t.getHotelId());
            });
            return new ServiceMessage<>(ServiceEnum.SUCCESS, hotelCurd.getHotelByIds(hotelIds));
        }
        return new ServiceMessage<>(ServiceEnum.NOT_FIND_NAME, null);
    }
}
