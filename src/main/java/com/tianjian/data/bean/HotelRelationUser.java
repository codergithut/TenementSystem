package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjian on 2018/12/31.
 */
@Entity
@Table(name = "HOTEL_RELATION_USER")
public class HotelRelationUser {

    /**
     * 关系ID
     */
    @Id
    private String relationId;

    /**
     * 酒店ID
     */
    private String hotelId;

    /**
     * 用户ID
     */
    private String userId;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
