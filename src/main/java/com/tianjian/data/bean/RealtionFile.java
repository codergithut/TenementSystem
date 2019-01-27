package com.tianjian.data.bean;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "HOTEL_IMAGE_FILE")
public class RealtionFile {

    @Id
    /**
     * 记录ID
     */
    private String relationFileId;


    /**
     * 资源code
     */
    private String resourceCode;

    /**
     * 资源外部关联ID
     */
    private String realtionId;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getRealtionId() {
        return realtionId;
    }

    public void setRealtionId(String realtionId) {
        this.realtionId = realtionId;
    }

    public String getRelationFileId() {
        return relationFileId;
    }

    public void setRelationFileId(String relationFileId) {
        this.relationFileId = relationFileId;
    }
}

