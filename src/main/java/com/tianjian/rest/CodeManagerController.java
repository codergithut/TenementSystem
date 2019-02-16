package com.tianjian.rest;

import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.CodeMessage;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.CodeManager;
import com.tianjian.service.ServiceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/code")
public class CodeManagerController {

    @Autowired
    CodeManager codeManager;

    @Autowired
    UserCurd userCurd;

    @PostMapping(value = "/getCodeToEmail")
    public ResponseData<Boolean> sendCodeToEmail(@RequestBody CodeMessage codeMessage) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        UserDO userDO = userCurd.findByAccount(codeMessage.getAccount());
        if(userDO != null) {
            ServiceMessage<Boolean> data = new ServiceMessage<Boolean>(ServiceEnum.NOT_FIND_NAME,false);
            return responseData.buildResponseDataByCode(data);
        }
        ServiceMessage<Boolean> data = codeManager.sendCodeToMessage
                (codeMessage.getType(), codeMessage.getExpireTime(), userDO.getUserId(), userDO.getEmail());
        return responseData.buildResponseDataByCode(data);
    }

}
