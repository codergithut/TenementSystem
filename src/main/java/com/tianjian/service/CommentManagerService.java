package com.tianjian.service;

import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.model.ServiceMessage;

import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
public interface CommentManagerService {

    /**
     * 查找评论信息
     * @param commentDO
     * @return
     */
    ServiceMessage<List<CommentDO>> findCommentDO(CommentDO commentDO);

    /**
     * 保存评论信息
     * @param commentDO
     * @return
     */
    ServiceMessage<CommentDO> saveCommentDO(CommentDO commentDO);


    /**
     * 删除评论信息
     * @param commentId
     * @return
     */
    ServiceMessage deleteCommentDO(String commentId);

}