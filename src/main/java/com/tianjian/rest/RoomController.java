package com.tianjian.rest;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.RoomDO;
import com.tianjian.data.service.RoomCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.RoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 房间api数据接口
 * Created by tianjian on 2019/1/12.
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    RoomManagerService roomManagerService;

    @Autowired
    RoomCurd roomCurd;

    /**
     * 添加房间信息
     * @param roomDO 房间基础数据
     * @return 业务数据封装
     */
    @PostMapping("/add")
    public ResponseData<Boolean> addRoom(@RequestBody RoomDO roomDO) {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        ServiceMessage<Boolean> data = roomManagerService.addRoomInfo(roomDO);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 根据房间ID删除房间数据
     * @param roomId 房间ID
     * @return 业务数据封装
     */
    @GetMapping("/deleteByRoomId")
    public ResponseData<Boolean> deleteByRoomId(String roomId) {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        ServiceMessage<Boolean> data = roomManagerService.deleteRoomByRoomId(roomId);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 删除某个酒店下的房间信息
     * @param hotelId 酒店ID
     * @return 业务数据封装
     */
    @GetMapping("/deleteByHotelId")
    public ResponseData<Boolean> deleteByHotelId(String hotelId) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        ServiceMessage<Boolean> data = roomManagerService.deleteRoomByHotelId(hotelId);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 根据酒店ID信息获取房间信息
     * @param hotelId 酒店ID
     * @return 返回数据封装
     */
    @GetMapping("/findRoomByHotelId")
    public ResponseData<List<RoomDO>> findByHotelId(String hotelId) {
        ResponseData<List<RoomDO>> responseData = new ResponseData<>();
        ServiceMessage<List<RoomDO>> data = roomManagerService.findRoomInfoByHotelId(hotelId);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 根据房间ID后去房间信息
     * @param roomId 房间ID
     * @return 返回数据封装
     */
    @GetMapping("/findRoomByRoomId")
    public ResponseData<RoomDO> findByRoomId(String roomId) {
        ResponseData<RoomDO> responseData = new ResponseData<>();
        ServiceMessage<RoomDO> data = roomManagerService.findRoomInfoByRoomId(roomId);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 测试接口
     * @return 返回所有的房间信息
     */
    @GetMapping("/all")
    public Object all() {
        return roomCurd.findAll();
    }



}
