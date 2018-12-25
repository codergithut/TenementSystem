package com.tianjian.model.bean;

/**
 * Created by tianjian on 2018/11/29.
 */
public class Tenant {

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户身份证号码
     */
    private String identity;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private int age;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
