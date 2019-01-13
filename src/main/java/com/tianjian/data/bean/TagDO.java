package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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


    //set get .....
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
