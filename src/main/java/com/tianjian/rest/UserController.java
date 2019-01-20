package com.tianjian.rest;

import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.UserDO;
import com.tianjian.model.UserManageModel;
import com.tianjian.model.view.ResponseData;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.HotelRelationUserManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.service.UserManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.tianjian.config.Constract.MANAGER;

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
    public ResponseData<List<UserManageModel>> getAllUser(String role) {
        List<UserManageModel> datas = new ArrayList<UserManageModel>();
        List<UserDO> userDOs = userManagerService.findUserDO(role).getData();
        if(MANAGER.equals(role) && userDOs != null && userDOs.size() > 0 ) {
            for(UserDO userDO : userDOs) {
                UserManageModel userManageModel = new UserManageModel();
                BeanUtils.copyProperties(userDO, userManageModel);
                ServiceMessage<List<HotelRelationUser>> relations = hotelRelationUserManagerService
                        .findHotelIDsByUserId(userDO.getUserId());
                if(ServiceEnum.SUCCESS.getCode() == (relations.getServiceEnum().getCode())) {
                    List<String> allHotel = new ArrayList<String>();
                    if(relations != null && relations.getData() != null && relations.getData().size() > 0) {
                        for(HotelRelationUser relationUser : relations.getData()) {
                            allHotel.add(relationUser.getHotelId());
                        }
                    }
                    userManageModel.setHotel(allHotel.toArray(new String[allHotel.size()]));
                }

                datas.add(userManageModel);
            }
        }

        return new ResponseData<List<UserManageModel>>(ServiceEnum.SUCCESS.getMsg(),datas, ServiceEnum.SUCCESS.getCode());
    }

    /**
     * 修改或添加用户关联关系，会更新用户信息和用户和酒店的关联关系信息
     * @param userManageModel 酒店管理模型
     * @return 业务封装信息
     */
    @PostMapping("/addUser")
    public ResponseData<UserDO> editManager(@RequestBody UserManageModel userManageModel) {
        ResponseData<UserDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(userManagerService.editManager(userManageModel));

    }

}
