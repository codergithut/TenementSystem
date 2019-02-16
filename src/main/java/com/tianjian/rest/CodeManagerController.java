package com.tianjian.rest;

import com.alibaba.fastjson.JSON;
import com.tianjian.model.CodeMessage;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.CodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeManagerController {

    @Autowired
    CodeManager codeManager;

    @PostMapping(value = "/getCodeToEmail")
    public ServiceMessage<Boolean> sendCodeToEmail(@RequestBody CodeMessage codeMessage) {
        return codeManager.sendCodeToMessage
                (codeMessage.getType(), codeMessage.getExpireTime(), codeMessage.getUserId());
    }

    public static void main(String[] args) {
        CodeMessage codeMessage = new CodeMessage();
        codeMessage.setType("update");
        codeMessage.setExpireTime(1800L);
        codeMessage.setUserId("hhah");
        System.out.println(JSON.toJSONString(codeMessage));
    }

}
