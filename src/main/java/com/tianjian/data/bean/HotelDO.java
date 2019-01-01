package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjian on 2018/12/31.
 */
@Entity
@Table(name = "HOTEL")
public class HotelDO {

    /**
     * 酒店ID
     */
    @Id
    private String hotelId;

    /**
     * 酒店名称
     */
    private String name;

    /**
     * 酒店别名
     */
    private String alias;

    /**
     * 酒店位置
     */
    private String location;

    /**
     * 酒店图片
     */
    private String img;

    /**
     * 酒店信息
     */
    private String content;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
