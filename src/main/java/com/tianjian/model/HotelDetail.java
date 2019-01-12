package com.tianjian.model;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.RoomDO;

import java.util.List;

/**
 * Created by tianjian on 2019/1/12.
 */
public class HotelDetail {
    private HotelDO hotelInfo;

    private List<RoomDO> roomsInfo;

    public HotelDO getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelDO hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public List<RoomDO> getRoomsInfo() {
        return roomsInfo;
    }

    public void setRoomsInfo(List<RoomDO> roomsInfo) {
        this.roomsInfo = roomsInfo;
    }
}
