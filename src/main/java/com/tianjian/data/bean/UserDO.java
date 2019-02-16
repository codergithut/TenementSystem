package com.tianjian.data.bean;

/**
 * Created by tianjian on 2018/11/29.
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户实体类
 * @since 2018-03-12
 */
@Entity
@Table(name = "USER")
public class UserDO {

    /**
     * 用户ID
     */
    @Id
    private String userId;

    /**
     * 用户名称
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 角色
     */
    private String role;

    /**
     * 是否激活 1 激活 0 未激活
     */
    private int active;

    /**
     * 用户名称
     */
    private String username;

    private Date date;

    private String operatingId;


    //set get .....


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperatingId() {
        return operatingId;
    }

    public void setOperatingId(String operatingId) {
        this.operatingId = operatingId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
