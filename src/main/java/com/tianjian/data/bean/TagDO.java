package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 标签数据
 * Created by tianjian on 2019/1/12.
 */
@Entity
@Table(name = "TAG")
public class TagDO {

    /**
     * 标签ID
     */
    @Id
    private String tagId;

    /**
     * 标签名称
     */
    private String name;

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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
