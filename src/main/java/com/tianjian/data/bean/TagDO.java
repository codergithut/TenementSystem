package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjian on 2019/1/12.
 */
@Entity
@Table(name = "TAG")
public class TagDO {

    /**
     *
     */
    @Id
    private String tagId;

    /**
     *
     */
    private String name;

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
