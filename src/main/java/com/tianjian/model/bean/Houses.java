package com.tianjian.model.bean;

/**
 * Created by tianjian on 2018/11/29.
 */
public class Houses {

    /**
     * 房屋ID
     */
    private String housesId;

    /**
     * 房屋编号
     */
    private String houseNumber;

    /**
     * 住址
     */
    private String houseAddress;

    /**
     * 承租人编号
     */
    private String tenantId;

    /**
     * 合同编号
     */
    private String contractId;

    public String getHousesId() {
        return housesId;
    }

    public void setHousesId(String housesId) {
        this.housesId = housesId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
