package com.tianjian.service;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.model.ServiceMessage;

import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
public interface HotelManagerService {

    /**
     * 查找hotel信息
     * @param hotelDO
     * @return
     */
    ServiceMessage<List<HotelDO>> findHotelDO(HotelDO hotelDO);

    /**
     * 保存酒店信息
     * @param hotelDO
     * @return
     */
    ServiceMessage<HotelDO> saveHotelDO(HotelDO hotelDO);

}