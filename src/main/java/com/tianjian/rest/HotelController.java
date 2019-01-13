package com.tianjian.rest;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.HotelCurd;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.UserManagerService;
import com.tianjian.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/detail")
    public ResponseData<HotelDetail> searchHotelDetail(String hotelId) throws Exception {
        ResponseData<HotelDetail> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.getHotelDetail(hotelId));
    }



    @PostMapping("/add")
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

    @GetMapping("/search")
    public ResponseData<Page<HotelDO>> searchByUserId
            (@RequestParam(value="userId",required=true) String userId,
             @RequestParam(value="page",required=true) int page,
             @RequestParam(value="pageSize",required=true) int pageSize) {
        ResponseData<Page<HotelDO>> responseData = new ResponseData<>();
        PageRequest pageRequest = new PageRequest(page, pageSize);
        return responseData.buildResponseDataByCode(hotelManagerService.findHotelDO(userId, pageRequest));
    }


    //todo delete test
    @GetMapping("/findALL")
    public List<HotelDO> findAllHotel() {
        return hotelCurd.findAll();
    }

}
