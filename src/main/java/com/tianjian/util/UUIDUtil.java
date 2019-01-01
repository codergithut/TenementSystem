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

    public static void main(String[] args) throws Exception {
        UserDO userDO = new UserDO();
        userDO.setRole("USER");
        userDO.setPassword("123456");
        userDO.setEmail("1468198882@qq.com");
        userDO.setAccount("tianjian");
        userDO.setUserId(UUIDUtil.getPreUUID("USER"));
        System.out.println(JSONObject.toJSONString(userDO));
        System.out.println("-------------------------------");

        HotelDO hotelDO = new HotelDO();
        hotelDO.setName("牛逼哄哄的酒店");
        hotelDO.setLocation("SH");
        hotelDO.setImg("SSSS");
        hotelDO.setHotelId(UUIDUtil.getPreUUID("HOTEL"));
        hotelDO.setAlias("牛逼哄哄的酒店不需要别名");
        hotelDO.setContent("我是测试数据不要在意");
        System.out.println(JSONObject.toJSONString(hotelDO));

        CommentDO commentDO = new CommentDO();
        commentDO.setUserId("sssssss");
        commentDO.setHotelId("333333");
        commentDO.setComment("hahahah");
        commentDO.setCommentId(UUIDUtil.getPreUUID("COMMENT"));
        System.out.println(JSONObject.toJSONString(commentDO));

        HotelRelationUser hotelRelationUser = new HotelRelationUser();
        hotelRelationUser.setHotelId("8888");
        hotelRelationUser.setUserId("888888");
        hotelRelationUser.setRelationId(UUIDUtil.getPreUUID("RELATION"));
        System.out.println(JSONObject.toJSONString(hotelRelationUser));


    }
}
