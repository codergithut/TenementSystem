package com.tianjian.data.service;

import com.tianjian.data.bean.HotelDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianjian on 2018/11/29.
 * 酒店信息添删改查
 */
@Repository
public interface HotelCurd
        extends JpaRepository<HotelDO, String> {
    @Query(value = "select * from HOTEL s where s.hotel_id in ?1", nativeQuery = true)
    List<HotelDO> getHotelByIds(List<String> ids);
}
