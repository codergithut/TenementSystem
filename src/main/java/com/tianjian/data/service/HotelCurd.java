package com.tianjian.data.service;

import com.tianjian.data.bean.HotelDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tianjian on 2018/11/29.
 * 酒店信息添删改查
 */
@Repository
public interface HotelCurd
        extends JpaRepository<HotelDO, String> {
}
