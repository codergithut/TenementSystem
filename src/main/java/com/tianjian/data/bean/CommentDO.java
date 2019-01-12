package com.tianjian.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjian on 2018/12/31.
 */
@Entity
@Table(name = "COMMENT")
public class CommentDO {

    /**
     * 评论ID
     */
    @Id
    private String commentId;

    /**
     * 评论酒店的ID
     */
    private String roomId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String date;

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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
