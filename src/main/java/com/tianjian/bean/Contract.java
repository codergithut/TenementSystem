package com.tianjian.bean;

import java.util.Date;

/**
 * Created by tianjian on 2018/11/29.
 */
public class Contract {

    /**
     * 数据ID
     */
    private String contractId;

    /**
     * 合同基本条款
     */
    private String content;

    /**
     * 合同拟定时间
     */
    private Date createTime;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
