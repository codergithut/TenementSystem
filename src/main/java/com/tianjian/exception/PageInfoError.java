package com.tianjian.exception;

/**
 * 分页信息错误
 * Created by tianjian on 2019/1/13.
 */
public class PageInfoError extends Exception{
    public PageInfoError() {
        super("pageinfo error");
    }
}
