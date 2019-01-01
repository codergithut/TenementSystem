package com.tianjian.service.impl;

import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.service.CommentCurd;
import com.tianjian.data.service.HotelCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.CommentManagerService;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.ServiceEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianjian on 2019/1/1.
 */
@Service
public class CommentManagerServiceImpl implements CommentManagerService {

    @Autowired
    CommentCurd commentCurd;

    @Override
    public ServiceMessage<List<CommentDO>> findCommentDO(CommentDO commentDO) {
        List<CommentDO> datas = new ArrayList<>();

        Example<CommentDO> example = Example.of(commentDO);
        datas.addAll(commentCurd.findAll(example));

        if(datas.size() > 0) {
            return new ServiceMessage(ServiceEnum.SUCCESS, datas);
        } else {
            return new ServiceMessage(ServiceEnum.SEARCH_NULL, null);
        }

    }

    @Override
    public ServiceMessage<CommentDO> saveCommentDO(CommentDO commentDO) {
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
