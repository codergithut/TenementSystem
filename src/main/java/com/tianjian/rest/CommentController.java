package com.tianjian.rest;

import com.alibaba.fastjson.JSON;
import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.CommentCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.CommentManagerService;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.UserManagerService;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 用户评论对外暴露接口
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentManagerService commentManagerService;

    /**
     * 添加评论信息
     * @param commentDO 评论信息
     * @return 返回消息封装
     */
    @PostMapping("/add")
    public ResponseData<CommentDO> saveComment(@RequestBody CommentDO commentDO) {
        ResponseData<CommentDO> responseData = new ResponseData<CommentDO>();
        ServiceMessage<CommentDO> data = commentManagerService.saveCommentDO(commentDO);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 获取某个房间的评论信息
     * @param roomId 房间ID
     * @return 房间封装信息
     */
    @GetMapping("/search")
    public ResponseData<List<CommentDO>> searchComment(String roomId) {
        ResponseData<List<CommentDO>> responseData = new ResponseData<>();
        ServiceMessage<List<CommentDO>> data = commentManagerService.findCommentDO(roomId);
        return responseData.buildResponseDataByCode(data);
    }

    /**
     * 删除评论信息
     * @param commentId 评论数据ID
     * @return 业务返回信息封装
     */
    @GetMapping("/delete")
    public ResponseData deleteComment(@RequestParam(value="commentId",required=true)
                                                  String commentId) {
        ResponseData responseData = new ResponseData<>();
        ServiceMessage<List<CommentDO>> data = commentManagerService.deleteCommentDO(commentId);
        return responseData.buildResponseDataByCode(data);
    }

    //todo delete test
    @Autowired
    CommentCurd commentCurd;

    /**
     * 获取所有的评论信息用于测试
     * @return 所有的评论信息
     */
    @GetMapping("findAll")
    public List<CommentDO> getAllComment() {
        return commentCurd.findAll();
    }

}