package com.tianjian.data.service;

import com.tianjian.data.bean.RoomDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 房间信息基础数据操作
 * Created by tianjian on 2019/1/12.
 */
@Repository
public interface RoomCurd
        extends JpaRepository<RoomDO, String> {

    /**
     * 根据酒店ID获取房间信息
     * @param hotelId 酒店ID
     * @return 房间信息
     */
    List<RoomDO> findByHotelId(String hotelId);

    /**
     * 根据酒店ID删除对应的酒店房间信息
     * @param hotelId 酒店ID
     * @return 删除条目数
     */
    @Transactional
    Integer deleteByHotelId(String hotelId);

}
