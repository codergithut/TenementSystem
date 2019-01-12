package com.tianjian.rest;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.RoomDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.RoomCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.RoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2019/1/12.
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    RoomManagerService roomManagerService;

    @Autowired
    RoomCurd roomCurd;

    @PostMapping("/add")
    public ResponseData<Boolean> addRoom(@RequestBody RoomDO roomDO) throws Exception {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        ServiceMessage<Boolean> data = roomManagerService.addRoomInfo(roomDO);
        return responseData.buildResponseDataByCode(data);
    }

    @GetMapping("/deleteByRoomId")
    public ResponseData<Boolean> deleteByRoomId(String roomId) {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        ServiceMessage<Boolean> data = roomManagerService.deleteRoomByRoomId(roomId);
        return responseData.buildResponseDataByCode(data);
    }

    @GetMapping("/deleteByHotelId")
    public ResponseData<Boolean> deleteByHotelId(String hotelId) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        ServiceMessage<Boolean> data = roomManagerService.deleteRoomByHotelId(hotelId);
        return responseData.buildResponseDataByCode(data);
    }

    @GetMapping("/findRoomByHotelId")
    public ResponseData<List<RoomDO>> findByHotelId(String hotelId) {
        ResponseData<List<RoomDO>> responseData = new ResponseData<>();
        ServiceMessage<List<RoomDO>> data = roomManagerService.findRoomInfoByHotelId(hotelId);
        return responseData.buildResponseDataByCode(data);
    }

    @GetMapping("/findRoomByRoomId")
    public ResponseData<RoomDO> findByRoomId(String roomId) {
        ResponseData<RoomDO> responseData = new ResponseData<>();
        ServiceMessage<RoomDO> data = roomManagerService.findRoomInfoByRoomId(roomId);
        return responseData.buildResponseDataByCode(data);
    }

    @GetMapping("/all")
    public Object all() {
        return roomCurd.findAll();
    }

    public static void main(String[] args) {
        RoomDO roomDO = new RoomDO();
        roomDO.setRoomId(UUID.randomUUID().toString());
        roomDO.setHotelId("222");
        roomDO.setContent("ceshi");
        roomDO.setName("fangjian");
        roomDO.setPrice("$100");
        System.out.println(JSONObject.toJSONString(roomDO));
    }


}
