package com.tianjian.util;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.UserDO;

/**
 * Created by tianjian on 2018/12/31.
 */
public class UUIDUtil {
    public static String getPreUUID(String pre) throws Exception {
        if (pre == null) {
            throw new Exception("pre is not null");
        }
        return pre + ":" + java.util.UUID.randomUUID().toString();
    }
}
