package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 酒店用户关联关系数据
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

    private Date date;

    private String operatingId;


    //set get .....


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperatingId() {
        return operatingId;
    }

    public void setOperatingId(String operatingId) {
        this.operatingId = operatingId;
    }

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
