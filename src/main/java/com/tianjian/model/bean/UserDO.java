package com.tianjian.model.bean;

/**
 * Created by tianjian on 2018/11/29.
 */

import javax.persistence.*;

/**
 * 用户实体类
 *
 * @author 杨高超
 * @since 2018-03-12
 */
@Entity
@Table(name = "AUTH_USER")
public class UserDO {
    @Id
    @Column(length = 100)
    private String id;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String account;
    @Column(length = 64)
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
