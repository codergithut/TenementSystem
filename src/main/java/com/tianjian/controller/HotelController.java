package com.tianjian.controller;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.UserManagerService;
import com.tianjian.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelManagerService hotelManagerService;

    @PostMapping("/search")
    public ResponseData<List<HotelDO>> searchHotel(@RequestBody HotelDO hotelDO) throws Exception {
        ResponseData<List<HotelDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.findHotelDO(hotelDO));
    }


    @PostMapping("/edit")
    public ResponseData<HotelDO> saveOrUpdate(@RequestBody HotelDO hotelDO) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.saveHotelDO(hotelDO));
    }

    @PostMapping("/delete")
    public ResponseData deleteHotel(String hotelId) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.deleteHotelDO(hotelId));
    }

    @PostMapping("/searhByUserid")
    public ResponseData<List<HotelDO>> searchByUserId(String userId) {
        ResponseData<List<HotelDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.getHotelByUserIds(userId));
    }

}
