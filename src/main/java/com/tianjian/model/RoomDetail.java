package com.tianjian.model;

import com.tianjian.data.bean.RoomDO;

/**
 * Created by tianjian on 2019/1/27.
 */
public class RoomDetail {

    private RoomDO roomDO;
    private String picUrl;

    public RoomDO getRoomDO() {
        return roomDO;
    }

    public void setRoomDO(RoomDO roomDO) {
        this.roomDO = roomDO;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
