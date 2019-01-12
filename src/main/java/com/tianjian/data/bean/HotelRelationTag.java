package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjian on 2018/12/31.
 */
@Entity
@Table(name = "HOTEL_RELATION_TAG")
public class HotelRelationTag {

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
    private String tagId;

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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
