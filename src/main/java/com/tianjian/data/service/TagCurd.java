package com.tianjian.data.service;

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

    /**
     * 根据标签id列表获取所有标签记录
     * @param ids 标签列表
     * @return 标签记录
     */
    @Query(value = "select * from TAG s where s.tag_id in ?1", nativeQuery = true)
    List<TagDO> getTagByIds(List<String> ids);
}
