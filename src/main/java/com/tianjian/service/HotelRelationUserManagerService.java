package com.tianjian.service;

import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.model.ServiceMessage;

import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
public interface HotelRelationUserManagerService {

    /**
     * 查找评论信息
     * @param
     * @return
     */
    ServiceMessage<List<HotelRelationUser>> findHotelIDsByUserId(String userID);

    /**
     * 保存评论信息
     * @param hotelRelationUser
     * @return
     */
    ServiceMessage<HotelRelationUser> saveHotelRelationUser(HotelRelationUser hotelRelationUser);


    /**
     * 删除评论信息
     * @param relationId
     * @return
     */
    ServiceMessage deleteHotelRelationUser(String relationId);




}
