package com.tianjian.data.service;

import com.tianjian.data.bean.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tianjian on 2018/11/29.
 * 用户信息添删改查
 */
@Repository
public interface UserCurd
        extends JpaRepository<UserDO, String> {

    /**
     * 根据用户账号获取用户信息
     * @param account 用户账号
     * @return 用户信息
     */
    UserDO findByAccount(String account);
}
