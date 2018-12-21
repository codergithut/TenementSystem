package com.tianjian.bean;

import java.util.Date;

/**
 * Created by tianjian on 2018/11/29.
 */
public class Strategy {

    /**
     * 策略ID
     */
    private String strategyId;

    /**
     * 续租
     */
    private boolean renewal;

    /**
     * 催租时间
     */
    private Date rentpromotion;

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public boolean isRenewal() {
        return renewal;
    }

    public void setRenewal(boolean renewal) {
        this.renewal = renewal;
    }

    public Date getRentpromotion() {
        return rentpromotion;
    }

    public void setRentpromotion(Date rentpromotion) {
        this.rentpromotion = rentpromotion;
    }
}
