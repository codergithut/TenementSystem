package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjian on 2019/2/16.
 */
@Entity
@Table(name = "CODELOGDO")
public class CodeLogDO {

    /**
     * 邮件验证码
     */
    @Id
    private String code;

    private String refId;

    /**
     * 类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
