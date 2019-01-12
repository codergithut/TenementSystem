package com.tianjian.data.service;

import com.tianjian.data.bean.RoomDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tianjian on 2019/1/12.
 */
@Repository
public interface RoomCurd
        extends JpaRepository<RoomDO, String> {

    List<RoomDO> findByHotelId(String hotelId);

    @Transactional
    Integer deleteByHotelId(String hotelId);

}
