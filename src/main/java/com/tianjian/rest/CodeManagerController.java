package com.tianjian.rest;

import com.alibaba.fastjson.JSON;
import com.tianjian.data.bean.CommentDO;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CodeManagerController {

    @Autowired
    CodeManager codeManager;

    @Autowired
    UserCurd userCurd;

    @PostMapping(value = "/getCodeToEmail")
    public ResponseData<Boolean> sendCodeToEmail(@RequestBody CodeMessage codeMessage) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        Optional<UserDO> userDO = userCurd.findById(codeMessage.getRefId());
        if(!userDO.isPresent()) {
            ServiceMessage<Boolean> data = new ServiceMessage<Boolean>(ServiceEnum.NOT_FIND_NAME,false);
            return responseData.buildResponseDataByCode(data);
        }
        ServiceMessage<Boolean> data = codeManager.sendCodeToMessage
                (codeMessage.getType(), codeMessage.getExpireTime(), codeMessage.getRefId(), userDO.get().getEmail());
        return responseData.buildResponseDataByCode(data);
    }

}
