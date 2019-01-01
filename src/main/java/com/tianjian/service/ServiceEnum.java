package com.tianjian.service;


/**
 * Created by tianjian on 2018/12/31.
 */
public enum ServiceEnum {

    DUPLICATION_NAME(1, "用户名重复"),
    NOT_FIND_NAME(2, "未找到该用户"),
    SAVE_NULL(3, "保存结果失败"),
    SEARCH_NULL(4, "查询结果为空"),
    SUCCESS(0, "业务成功");


    /**
     * 业务执行代码
     */
    private Integer code;

    /**
     * 业务消息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ServiceEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
