package com.tianjian.model;


/**
 * Created by tianjian on 2019/2/16.
 */
public class CodeMessage {

    /**
     * 类型
     */
    private String type;

    /**
     * 过期时间
     */
    private Long expireTime;

    private String refId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
