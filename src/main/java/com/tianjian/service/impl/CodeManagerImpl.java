package com.tianjian.service.impl;

import com.tianjian.data.bean.CodeLogDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.CodeLogCurd;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.CodeManager;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.JMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by tianjian on 2019/2/16.
 */
@Service
public class CodeManagerImpl implements CodeManager {

    @Autowired
    private CodeLogCurd codeLogCurd;

    @Autowired
    private UserCurd userCurd;

    @Autowired
    JavaMailSender mailSender;//自动注入

    @Value("${spring.mail.username}")
    String fromMail;

    private static String content = "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<title>验证码邮件</title>"
            + "<meta name=\"content-type\" content=\"text/html; charset=UTF-8\">"
            + "</head>"
            + "<body>"
            + "<p style=\"color:#0FF\">${CODE}</p>"
            + "</body>"
            + "</html>"; // 可以用HTMl语言写

    @Override
    public ServiceMessage<Boolean> sendCodeToMessage(String type, Long expireTime, String userId, String email) {
        CodeLogDO codeLogDO = new CodeLogDO();
        Date date = new Date();
        codeLogDO.setCode(UUID.randomUUID().toString());
        codeLogDO.setCreateTime(date.getTime());
        codeLogDO.setExpireTime(expireTime);
        codeLogDO.setType(type);
        codeLogDO.setRefId(userId);

        boolean result =JMailUtil.sendMessageToUser
                (mailSender, email, fromMail, content.replace("${CODE}", codeLogDO.getCode()));

        if(result) {
            codeLogCurd.save(codeLogDO);
            return new ServiceMessage<>(ServiceEnum.SUCCESS,true);
        } else {
            return new ServiceMessage<>(ServiceEnum.MAIL_FAIL, false);
        }
    }

    @Override
    public ServiceMessage<Boolean> checkCodeMessage(String type, String code) {
        Optional<CodeLogDO> codeLogDO = codeLogCurd.findById(code);
        if(codeLogDO.isPresent()) {
            long exprieTime = codeLogDO.get().getCreateTime() + codeLogDO.get().getExpireTime();
            long now = new Date().getTime();
            if(now < exprieTime && type.equals(codeLogDO.get().getType())) {
                return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
            }
        }
        return new ServiceMessage<>(ServiceEnum.CODE_ERROR, false);
    }
}
