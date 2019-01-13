package com.tianjian.model.view;

import com.tianjian.model.ServiceMessage;

import java.util.List;

/**
 * 响应消息model
 * Created by tianjian on 2018/12/23.
 * 响应消息
 */
public class ResponseData<T> {

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应编码
     */
    private Integer code;

    /**
     * 前端展示字典说明
     */
    private Dictionary dictionary;

    //set get...
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseData<T> buildResponseDataByCode(ServiceMessage<T> code) {
        return new ResponseData<T>(code.getServiceEnum().getMsg(),
                code.getData(), code.getServiceEnum().getCode());
    }

    public ResponseData<T> buildResponseDataByCode(ServiceMessage<T> code, String msg) {
        return new ResponseData<T>(msg,
                code.getData(), code.getServiceEnum().getCode());
    }

    public ResponseData() {
    }

    public ResponseData(String msg, T data, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public class Dictionary {
        private String propertyName;
        private Object propertyValue;
        private List<Object> viewName;
    }
}
