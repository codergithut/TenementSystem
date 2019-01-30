package com.tianjian.service.impl;

import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.CommentCurd;
import com.tianjian.data.service.UserCurd;
import com.tianjian.init.ServerStartedReport;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.CommentManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 评论管理服务
 * Created by tianjian on 2019/1/1.
 */
@Service
public class CommentManagerServiceImpl implements CommentManagerService {

    private Logger logger = LoggerFactory.getLogger(CommentManagerServiceImpl.class);

    @Autowired
    CommentCurd commentCurd;

    @Autowired
    UserCurd userCurd;

    /**
     * 根据房间id获取评论
     * @param roomId 房间ID
     * @return 用户评论信息
     */
    @Override
    public ServiceMessage<List<CommentDO>> findCommentDO(String roomId) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        List<CommentDO> datas = new ArrayList<>();
        CommentDO commentDO = new CommentDO();
        commentDO.setRoomId(roomId);
        Example<CommentDO> example = Example.of(commentDO);
        List<CommentDO> all = commentCurd.findAll(example,sort);
        datas.addAll(all);
        if(datas.size() > 0) {
            return new ServiceMessage(ServiceEnum.SUCCESS, datas);
        } else {
            logger.info("can not find comment rootId={}", roomId);
            return new ServiceMessage(ServiceEnum.SUCCESS, null);
        }

    }

    /**
     * 保存用户评论信息
     * @param commentDO 用户评论数据
     * @return 业务封装信息
     */
    @Override
    public ServiceMessage<CommentDO> saveCommentDO(CommentDO commentDO) {
        if(StringUtils.isBlank(commentDO.getCommentId())) {
            commentDO.setCommentId(UUIDUtil.getPreUUID("comment"));
        }
        Optional<UserDO> userDO = userCurd.findById(commentDO.getUserId());
        if(!userDO.isPresent()) {
            return new ServiceMessage<>(ServiceEnum.NOT_FIND_NAME, null);
        }
        commentDO.setUsername(userDO.get().getUsername());
        commentDO.setDate(new Date());
        commentCurd.save(commentDO);
        return new ServiceMessage(ServiceEnum.SUCCESS,  null);
    }

    /**
     * 删除评论
     * @param commentId 评论ID
     * @return 业务封装信息
     */
    @Override
    public ServiceMessage deleteCommentDO(String commentId) {
        Optional<CommentDO> datas = commentCurd.findById(commentId);
        if(!datas.isPresent()) {
            logger.info("delete comment fail commentId={}", commentId);
            return new ServiceMessage(ServiceEnum.DELETE_NOT_FOUND, null);
        }
        commentCurd.deleteById(commentId);
        return new ServiceMessage(ServiceEnum.SUCCESS,  null);
    }
}
