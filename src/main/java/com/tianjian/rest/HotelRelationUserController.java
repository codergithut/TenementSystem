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
 * 酒店用户关系api
 * Created by tianjian on 2019/1/1.
 */
@RestController
@RequestMapping("/api/relation")
public class HotelRelationUserController {

    @Autowired
    HotelRelationUserManagerService hotelRelationUserManagerService;

    /**
     * 编辑或添加酒店用户关系信息
     * @param hotelRelationUser 关系对象
     * @return 业务封装消息
     */
    @PostMapping("/edit")
    public ResponseData<HotelRelationUser> edit(@RequestBody HotelRelationUser hotelRelationUser) {
        ResponseData<HotelRelationUser> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode
                (hotelRelationUserManagerService.saveHotelRelationUser(hotelRelationUser));
    }

    /**
     * 删除酒店关系信息
     * @param relationId 关系ID
     * @return 业务封装消息
     */
    @GetMapping("/delete")
    public ResponseData deleteHotel(@RequestParam(value="relationId",required=true)
                                                String relationId) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelRelationUserManagerService.deleteHotelRelationUser(relationId));
    }

    @Autowired
    HotelRelationUserCurd hotelRelationUserCurd;

    //todo delete test
    /**
     * 为方便查看接口数据提供的接口查询
     * @return 所有关系酒店人员关系数据
     */
    @GetMapping("/findAll")
    public List<HotelRelationUser> findAll() {
        return hotelRelationUserCurd.findAll();
    }

}
