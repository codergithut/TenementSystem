package com.tianjian.model.front;

import java.util.List;

/**
 * Created by tianjian on 2018/12/23.
 */
public class ResponseData<T> {

    private String msg;
    private List<T> data;
    private int code;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public ResponseData buildFailResponseData(String msg, int code) {
        ResponseData responseData = new ResponseData();
        responseData.setMsg(msg);
        responseData.setCode(code);
        return responseData;
    }

    public ResponseData<T> buidSuccessResponseData(String msg, List<T> data) {
        ResponseData responseData = new ResponseData();
        responseData.setMsg(msg);
        responseData.setCode(1);
        return responseData;
    }
}
