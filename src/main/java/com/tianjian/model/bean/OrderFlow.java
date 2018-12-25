package com.tianjian.model.bean;

/**
 * Created by tianjian on 2018/11/29.
 */
public class OrderFlow {

    /**
     * 订单流水号
     */
    private String serialNumber;

    /**
     * 电费
     */
    private float electricityFees;

    /**
     * 水费
     */
    private float waterFees;

    /**
     * 房租
     */
    private float rent;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public float getElectricityFees() {
        return electricityFees;
    }

    public void setElectricityFees(float electricityFees) {
        this.electricityFees = electricityFees;
    }

    public float getWaterFees() {
        return waterFees;
    }

    public void setWaterFees(float waterFees) {
        this.waterFees = waterFees;
    }

    public float getRent() {
        return rent;
    }

    public void setRent(float rent) {
        this.rent = rent;
    }
}
