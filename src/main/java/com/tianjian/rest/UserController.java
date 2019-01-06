package com.tianjian.rest;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.view.ResponseData;
import com.tianjian.model.ServiceMessage;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private HotelManagerService hotelManagerService;

    @PostMapping("/register")
    public ResponseData<Boolean> registerUser(@RequestBody UserDO userDO) throws Exception {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        ServiceMessage<Boolean> data = userManagerService.registerUser(userDO);
        return responseData.buildResponseDataByCode(data);
    }

    @GetMapping("/unregister")
    public ResponseData<Boolean> unRegisterUser(@RequestParam(value="userId",required=true)
            String userId) throws Exception {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        ServiceMessage<Boolean> data = userManagerService.unRegisterUser(userDO);
        return responseData.buildResponseDataByCode(data);
    }


    @GetMapping("/getAllUser")
    public ResponseData<List<UserDO>> getAllUser() {
        ResponseData<List<UserDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(userManagerService.findUserDO());
    }


    public static void main(String[] args) {
        UserDO userDO = new UserDO();
        userDO.setRole("USER");
        userDO.setAccount("tianjian");
        userDO.setEmail("14sdfasdf@qq.com");
        userDO.setPassword("hahah");
        userDO.setUserId("userID");
        System.out.println(JSONObject.toJSONString(userDO));

        HotelRelationUser hotelRelationUser = new HotelRelationUser();
        hotelRelationUser.setUserId("test");
        hotelRelationUser.setRelationId("relationID");
        hotelRelationUser.setHotelId("0f3b0e39-9111-47c4-ace8-4f250a6cd05d");
        System.out.println(JSONObject.toJSONString(hotelRelationUser));

        CommentDO commentDO = new CommentDO();
        commentDO.setCommentId("commentid");
        commentDO.setUserId("userid");
        commentDO.setComment("comment");
        commentDO.setHotelId("hoteId");

        System.out.println(JSONObject.toJSONString(commentDO));

        HotelDO hotelDO = new HotelDO();
        hotelDO.setHotelId("hotelId");
        hotelDO.setContent("content");
        hotelDO.setAlias("alias");
        hotelDO.setImg("ceshi");
        hotelDO.setLocation("SH");
        hotelDO.setName("SH");

        System.out.println(JSONObject.toJSONString(hotelDO));


    }

}
