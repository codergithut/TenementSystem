package com.tianjian.rest;

import com.tianjian.data.bean.UserDO;
import com.tianjian.model.UserManageModel;
import com.tianjian.model.view.ResponseData;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.HotelRelationUserManagerService;
import com.tianjian.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息数据接口
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private HotelManagerService hotelManagerService;

    @Autowired
    private HotelRelationUserManagerService hotelRelationUserManagerService;

    /**
     * 用户注册
     * @param userDO 用户注册信息
     * @return 业务封装
     */
    @PostMapping("/register")
    public ResponseData<Boolean> registerUser(@RequestBody UserDO userDO) {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        ServiceMessage<Boolean> data = userManagerService.registerUser(userDO);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 账户注销
     * @param userId 用户ID
     * @return 业务封装
     */
    @GetMapping("/unregister")
    public ResponseData<Boolean> unRegisterUser(@RequestParam(value="userId",required=true)
            String userId) {
        ResponseData<Boolean> responseData = new ResponseData<Boolean>();
        ServiceMessage<Boolean> data = userManagerService.unRegisterUser(userId);
        return responseData.buildResponseDataByCode(data);
    }


    /**
     * 获取所有用户信息
     * @return 用户信息列表
     */
    @GetMapping("/getAllUserByRole")
    public ResponseData<List<UserDO>> getAllUser(String role) {
        ResponseData<List<UserDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(userManagerService.findUserDO(role));
    }

    /**
     * 修改或添加用户关联关系，会更新用户信息和用户和酒店的关联关系信息
     * @param userManageModel 酒店管理模型
     * @return 业务封装信息
     */
    @PostMapping("/addManager")
    public ResponseData editManager(@RequestBody UserManageModel userManageModel) {
        ResponseData responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(userManagerService.editManager(userManageModel));

    }

}
