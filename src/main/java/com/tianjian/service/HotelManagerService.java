package com.tianjian.service;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.ServiceMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
public interface HotelManagerService {

    /**
     * 查找hotel信息
     * @param userId
     * @return
     */
    ServiceMessage<Page<HotelDO>> findHotelDO(String userId, Pageable pageable);

    /**
     * 保存酒店信息
     * @param hotelDO
     * @return
     */
    ServiceMessage<HotelDO> saveHotelDO(HotelDO hotelDO);


    /**
     * 删除酒店信息
     * @param hotelId
     * @return
     */
    ServiceMessage deleteHotelDO(String hotelId);


    ServiceMessage<HotelDetail> getHotelDetail(String hotelId);
}
