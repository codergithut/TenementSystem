package com.tianjian.data.service;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.TagDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianjian on 2018/11/29.
 * 酒店信息添删改查
 */
@Repository
public interface TagCurd
        extends JpaRepository<TagDO, String> {
}
