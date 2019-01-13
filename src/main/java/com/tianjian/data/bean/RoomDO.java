package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房间数据
 * Created by tianjian on 2019/1/12.
 */
@Entity
@Table(name = "ROOM")
public class RoomDO {

    /**
     * 房间ID
     */
    @Id
    private String roomId;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 房间价格
     */
    private String price;

    /**
     * 房间描述
     */
    private String content;

    /**
     * 酒店ID
     */
    private String hotelId;


    //set get .....
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
