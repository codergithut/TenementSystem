package com.tianjian.service.impl;

import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.CommentCurd;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.CommentManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
@Service
public class CommentManagerServiceImpl implements CommentManagerService {

    @Autowired
    CommentCurd commentCurd;

    @Autowired
    UserCurd userCurd;

    @Override
    public ServiceMessage<List<CommentDO>> findCommentDO(String roomId) {
        List<CommentDO> datas = new ArrayList<>();
        CommentDO commentDO = new CommentDO();
        commentDO.setRoomId(roomId);
        Example<CommentDO> example = Example.of(commentDO);
        List<CommentDO> all = commentCurd.findAll(example);
        datas.addAll(all);
        if(datas.size() > 0) {
            return new ServiceMessage(ServiceEnum.SUCCESS, datas);
        } else {
            return new ServiceMessage(ServiceEnum.SEARCH_NULL, null);
        }

    }

    @Override
    public ServiceMessage<CommentDO> saveCommentDO(CommentDO commentDO) throws Exception {
        if(StringUtils.isBlank(commentDO.getCommentId())) {
            commentDO.setCommentId(UUIDUtil.getPreUUID("comment"));
        }
        UserDO userDO = userCurd.findById(commentDO.getUserId()).get();
        commentDO.setUsername(userDO.getUsername());
        commentDO.setDate(new Date().toString());
        CommentDO save = commentCurd.save(commentDO);
        if(save != null) {
            return new ServiceMessage(ServiceEnum.SUCCESS,  save);
        } else {
            return new ServiceMessage(ServiceEnum.SAVE_NULL,  null);
        }

    }

    @Override
    public ServiceMessage deleteCommentDO(String commentId) {
        commentCurd.deleteById(commentId);
        return new ServiceMessage(ServiceEnum.SUCCESS,  null);
    }
}
