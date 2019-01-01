package com.tianjian.data.service;

import com.tianjian.data.bean.HotelRelationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianjian on 2018/12/31.
 * 酒店信息添删改查
 */
@Repository
public interface HotelRelationUserCurd
        extends JpaRepository<HotelRelationUser, String> {
    List<HotelRelationUser> findByUserId(String userId);
}
