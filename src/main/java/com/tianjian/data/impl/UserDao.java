package com.tianjian.data.impl;

import com.tianjian.model.bean.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tianjian on 2018/11/29.
 */
@Repository
public interface UserDao extends JpaRepository<UserDO, String> {
    UserDO findByName(String userName);
}
