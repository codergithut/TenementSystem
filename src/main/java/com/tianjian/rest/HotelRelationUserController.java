package com.tianjian.rest;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.service.HotelRelationUserCurd;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.HotelRelationUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianjian on 2019/1/1.
 */
@RestController
@RequestMapping("/api/relation")
public class HotelRelationUserController {

    @Autowired
    HotelRelationUserManagerService hotelRelationUserManagerService;

    @PostMapping("/edit")
    public ResponseData<HotelRelationUser> edit(@RequestBody HotelRelationUser hotelRelationUser) {
        ResponseData<HotelRelationUser> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode
                (hotelRelationUserManagerService.saveHotelRelationUser(hotelRelationUser));
    }

    @PostMapping("/delete")
    public ResponseData deleteHotel(@RequestBody  String relationId) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelRelationUserManagerService.deleteHotelRelationUser(relationId));
    }

}
