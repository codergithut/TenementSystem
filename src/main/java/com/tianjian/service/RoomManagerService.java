package com.tianjian.service;

import com.tianjian.data.bean.RoomDO;
import com.tianjian.model.ServiceMessage;

import java.util.List;

/**
 * Created by tianjian on 2019/1/12.
 */
public interface RoomManagerService {

    ServiceMessage<List<RoomDO>> findRoomInfoByHotelId(String hotelId);

    ServiceMessage<Boolean> deleteRoomByRoomId(String roomId);

    ServiceMessage<Boolean> addRoomInfo(RoomDO roomDO);

    ServiceMessage<Boolean> deleteRoomByHotelId(String hotelId);

    ServiceMessage<RoomDO> findRoomInfoByRoomId(String roomId);

}
