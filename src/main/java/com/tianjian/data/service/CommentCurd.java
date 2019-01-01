package com.tianjian.data.service;

import com.tianjian.data.bean.CommentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tianjian on 2018/12/31.
 * 评论信息添删改查
 */
@Repository
public interface CommentCurd
        extends JpaRepository<CommentDO, String> {
}
