package com.tianjian.model;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationTag;
import com.tianjian.data.bean.RoomDO;

import java.util.List;

/**
 * 酒店详情model
 * Created by tianjian on 2019/1/12.
 */
public class HotelDetail {

    /**
     * 酒店基础信息
     */
    private HotelDO hotelInfo;

    private List<HotelRelationTag> hotelRelationTags;

    private List<String> tags;

    /**
     * 酒店房间详情
     */
    private List<RoomDO> roomsInfo;

    public List<HotelRelationTag> getHotelRelationTags() {
        return hotelRelationTags;
    }

    public void setHotelRelationTags(List<HotelRelationTag> hotelRelationTags) {
        this.hotelRelationTags = hotelRelationTags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    //set get
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
