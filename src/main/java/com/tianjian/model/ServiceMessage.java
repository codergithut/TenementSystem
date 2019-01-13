package com.tianjian.model;

import com.tianjian.service.ServiceEnum;

/**
 * Created by tianjian on 2018/12/31.
 */
public class ServiceMessage<T> {

    /**
     * 业务编码
     */
    private ServiceEnum serviceEnum;

    /**
     * 返回数据
     */
    private T data;

    public ServiceMessage(ServiceEnum serviceEnum, T data) {
        this.serviceEnum = serviceEnum;
        this.data = data;
    }

    //set get...
    public ServiceEnum getServiceEnum() {
        return serviceEnum;
    }

    public void setServiceEnum(ServiceEnum serviceEnum) {
        this.serviceEnum = serviceEnum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
