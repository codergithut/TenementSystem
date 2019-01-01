package com.tianjian.controller;

import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.CommentManagerService;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.UserManagerService;
import com.tianjian.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentManagerService commentManagerService;

    @PostMapping("/edit")
    public ResponseData<CommentDO> saveComment(@RequestBody CommentDO commentDO) throws Exception {
        ResponseData<CommentDO> responseData = new ResponseData<CommentDO>();
        commentDO.setUserId(UUIDUtil.getPreUUID("comment"));
        ServiceMessage<CommentDO> data = commentManagerService.saveCommentDO(commentDO);
        return responseData.buildResponseDataByCode(data);
    }

    @PostMapping("/search")
    public ResponseData<List<CommentDO>> searchComment(@RequestBody CommentDO commentDO) throws Exception {
        ResponseData<List<CommentDO>> responseData = new ResponseData<>();
        ServiceMessage<List<CommentDO>> data = commentManagerService.findCommentDO(commentDO);
        return responseData.buildResponseDataByCode(data);
    }

    @PostMapping("/delete")
    public ResponseData deleteComment(String commentId) throws Exception {
        ResponseData responseData = new ResponseData<>();
        ServiceMessage<List<CommentDO>> data = commentManagerService.deleteCommentDO(commentId);
        return responseData.buildResponseDataByCode(data);
    }
}
