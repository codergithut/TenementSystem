package com.tianjian.service;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.ServiceMessage;

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
    ServiceMessage<List<HotelDO>> findHotelDO(String userId);

    /**
     * 保存酒店信息
     * @param hotelDO
     * @return
     */
    ServiceMessage<HotelDO> saveHotelDO(HotelDO hotelDO) throws Exception;


    /**
     * 删除酒店信息
     * @param hotelId
     * @return
     */
    ServiceMessage deleteHotelDO(String hotelId);


    ServiceMessage<HotelDetail> getHotelDetail(String hotelId);
}
