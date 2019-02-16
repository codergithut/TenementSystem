package com.tianjian.service;


/**
 * Created by tianjian on 2018/12/31.
 */
public enum ServiceEnum {

    DUPLICATION_NAME(1, "用户名重复"),
    NOT_FIND_NAME(2, "未找到该用户"),
    SAVE_NULL(3, "保存结果失败"),
    SEARCH_NULL(4, "查询结果为空"),
    FAIL(5,"系统错误，请联系管理员"),
    FAIL_FIND_RECORD(6,"未能找到记录"),
    DELETE_NOT_FOUND(7,"删除失败未能找到相关记录"),
    MAIL_FAIL(8,"邮件发送失败"),
    CODE_ERROR(9,"验证码验证失败"),
    UNACTIVE_ERROR(10,"账号未激活"),
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
