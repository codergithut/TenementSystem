package com.tianjian.rest;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.HotelCurd;
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

    @Autowired
    private HotelCurd hotelCurd;

    @PostMapping("/search")
    public ResponseData<List<HotelDO>> searchHotel(@RequestBody HotelDO hotelDO) throws Exception {
        ResponseData<List<HotelDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.findHotelDO(hotelDO));
    }


    @PostMapping("/save")
    public ResponseData<HotelDO> saveOrUpdate(@RequestBody HotelDO hotelDO) throws Exception {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.saveHotelDO(hotelDO));
    }

    @GetMapping("/delete")
    public ResponseData deleteHotel(@RequestParam(value="hotelId",required=true)
            String hotelId) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.deleteHotelDO(hotelId));
    }

    @GetMapping("/searhByUserid")
    public ResponseData<List<HotelDO>> searchByUserId
            (@RequestParam(value="userId",required=true) String userId) {
        ResponseData<List<HotelDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.getHotelByUserIds(userId));
    }


    //todo delete test
    @GetMapping("/findALL")
    public List<HotelDO> findAllHotel() {
        return hotelCurd.findAll();
    }

}
