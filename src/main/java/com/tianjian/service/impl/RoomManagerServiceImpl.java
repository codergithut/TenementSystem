package com.tianjian.service.impl;

import com.tianjian.data.bean.RoomDO;
import com.tianjian.data.service.RoomCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.RoomManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianjian on 2019/1/12.
 */
@Service
public class RoomManagerServiceImpl implements RoomManagerService {

    @Autowired
    RoomCurd roomCurd;

    @Override
    public ServiceMessage<List<RoomDO>> findRoomInfoByHotelId(String hotelId) {
        List<RoomDO> roomDOS = roomCurd.findByHotelId(hotelId);
        return new ServiceMessage<List<RoomDO>>(ServiceEnum.SUCCESS, roomDOS);
    }

    @Override
    public ServiceMessage<Boolean> deleteRoomByRoomId(String roomId) {
        roomCurd.deleteById(roomId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    @Override
    public ServiceMessage<Boolean> addRoomInfo(RoomDO roomDO) {
        if(StringUtils.isBlank(roomDO.getRoomId())) {
            try {
                roomDO.setRoomId(UUIDUtil.getPreUUID("ROOM"));
            } catch (Exception e) {
                e.printStackTrace();
                return new ServiceMessage<>(ServiceEnum.NOT_FIND_NAME, false);
            }
        }
        roomCurd.save(roomDO);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    @Override
    public ServiceMessage<Boolean> deleteRoomByHotelId(String hotelId) {
        roomCurd.deleteByHotelId(hotelId);
        return new ServiceMessage<Boolean>(ServiceEnum.SUCCESS, true);
    }

    @Override
    public ServiceMessage<RoomDO> findRoomInfoByRoomId(String roomId) {
        return new ServiceMessage<RoomDO>(ServiceEnum.SUCCESS, roomCurd.findById(roomId).get());
    }
}
