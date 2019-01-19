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
import java.util.Optional;

/**
 * 房间管理业务类
 * Created by tianjian on 2019/1/12.
 */
@Service
public class RoomManagerServiceImpl implements RoomManagerService {

    @Autowired
    RoomCurd roomCurd;

    /**
     * 根据酒店ID获取房间信息
     * @param hotelId 酒店ID
     * @return 房间信息列表
     */
    @Override
    public ServiceMessage<List<RoomDO>> findRoomInfoByHotelId(String hotelId) {
        List<RoomDO> roomDOS = roomCurd.findByHotelId(hotelId);
        return new ServiceMessage<List<RoomDO>>(ServiceEnum.SUCCESS, roomDOS);
    }

    /**
     *
     * @param roomId
     * @return
     */
    @Override
    public ServiceMessage<Boolean> deleteRoomByRoomId(String roomId) {
        Optional<RoomDO> data = roomCurd.findById(roomId);
        if(!data.isPresent()) {
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }
        roomCurd.deleteById(roomId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    /**
     *
     * @param roomDO
     * @return
     */
    @Override
    public ServiceMessage<RoomDO> addRoomInfo(RoomDO roomDO) {
        if(StringUtils.isBlank(roomDO.getRoomId())) {
            roomDO.setRoomId(UUIDUtil.getPreUUID("ROOM"));
        }
        RoomDO rommDo = roomCurd.save(roomDO);
        return new ServiceMessage<RoomDO>(ServiceEnum.SUCCESS, rommDo);
    }

    /**
     *
     * @param hotelId
     * @return
     */
    @Override
    public ServiceMessage<Boolean> deleteRoomByHotelId(String hotelId) {
        List<RoomDO>  datas = roomCurd.findByHotelId(hotelId);
        if(datas == null && datas.size() == 0) {
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }
        roomCurd.deleteByHotelId(hotelId);
        return new ServiceMessage<Boolean>(ServiceEnum.SUCCESS, true);
    }

    /**
     *
     * @param roomId
     * @return
     */
    @Override
    public ServiceMessage<RoomDO> findRoomInfoByRoomId(String roomId) {
        return new ServiceMessage<RoomDO>(ServiceEnum.SUCCESS, roomCurd.findById(roomId).get());
    }
}
