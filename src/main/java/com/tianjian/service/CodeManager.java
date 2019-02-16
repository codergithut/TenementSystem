package com.tianjian.service;

import com.tianjian.model.ServiceMessage;

/**
 * Created by tianjian on 2019/2/16.
 */
public interface CodeManager {
    ServiceMessage<Boolean> sendCodeToMessage(String type, Long expireTime, String userId, String email);
    ServiceMessage<Boolean> checkCodeMessage(String type, String code);
}
