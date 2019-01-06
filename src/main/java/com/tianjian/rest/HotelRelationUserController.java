package com.tianjian.rest;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.service.HotelRelationUserCurd;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.HotelRelationUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
@RestController
@RequestMapping("/api/relation")
public class HotelRelationUserController {

    @Autowired
    HotelRelationUserManagerService hotelRelationUserManagerService;

    @PostMapping("/edit")
    public ResponseData<HotelRelationUser> edit(@RequestBody HotelRelationUser hotelRelationUser) throws Exception {
        ResponseData<HotelRelationUser> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode
                (hotelRelationUserManagerService.saveHotelRelationUser(hotelRelationUser));
    }

    @GetMapping("/delete")
    public ResponseData deleteHotel(@RequestParam(value="relationId",required=true)
                                                String relationId) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelRelationUserManagerService.deleteHotelRelationUser(relationId));
    }

    @Autowired
    HotelRelationUserCurd hotelRelationUserCurd;

    //todo delete test
    @GetMapping("/findAll")
    public List<HotelRelationUser> findAll() {
        return hotelRelationUserCurd.findAll();
    }

}
