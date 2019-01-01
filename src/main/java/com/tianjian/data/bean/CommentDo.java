package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjian on 2018/12/31.
 */
@Entity
@Table(name = "COMMENT")
public class CommentDo {

    /**
     * 评论ID
     */
    @Id
    private String commentId;

    /**
     * 评论酒店的ID
     */
    private String hotelId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 评论
     */
    private String comment;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
