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
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentManagerService commentManagerService;

    @PostMapping("/edit")
    public ResponseData<CommentDO> saveComment(@RequestBody CommentDO commentDO) throws Exception {
        if(StringUtils.isBlank(commentDO.getCommentId())) {
            commentDO.setCommentId(UUIDUtil.getPreUUID("COMMIT"));
        }
        ResponseData<CommentDO> responseData = new ResponseData<CommentDO>();
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
    public ResponseData deleteComment(@RequestBody String commentId) throws Exception {
        ResponseData responseData = new ResponseData<>();
        ServiceMessage<List<CommentDO>> data = commentManagerService.deleteCommentDO(commentId);
        return responseData.buildResponseDataByCode(data);
    }

    //todo delete test
    @Autowired
    CommentCurd commentCurd;

    @GetMapping("findAll")
    public List<CommentDO> getAllComment() {
        return commentCurd.findAll();
    }

    public static void main(String[] args) {
        CommentDO commentDO = new CommentDO();
        commentDO.setCommentId(UUID.randomUUID().toString());
        commentDO.setComment("this is test");
        commentDO.setHotelId(UUID.randomUUID().toString());
        commentDO.setUserId("test");
        System.out.println(JSON.toJSONString(commentDO));
    }

}