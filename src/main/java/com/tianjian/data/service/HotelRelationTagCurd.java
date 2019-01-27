package com.tianjian.data.service;

import com.tianjian.data.bean.HotelRelationTag;
import com.tianjian.data.bean.HotelRelationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tianjian on 2018/12/31.
 * 酒店信息添删改查
 */
@Repository
public interface HotelRelationTagCurd
        extends JpaRepository<HotelRelationTag, String> {

    /**
     * 获取酒店关联关系数据
     * @param hotelId 酒店ID
     * @return 关联关系实体
     */
    List<HotelRelationTag> findByHotelIdOrderByDateDesc(String hotelId);

    /**
     * 根据酒店ID删除关联关系
     * @param hotelId 酒店ID
     * @return 删除的条数
     */
    @Transactional
    Integer deleteByHotelId(String hotelId);

    /**
     * 根据TagID查询关联关系
     * @param tagId 标记ID
     * @return 关联关系对象
     */
    List<HotelRelationTag> findByTagIdOrderByDateDesc(String tagId);

    /**
     * 根据TagId删除关联关系
     * @param tagId 标记ID
     * @return 删除的条目数
     */
    @Transactional
    Integer deleteByTagId(String tagId);

    @Query(value = "select * from HOTEL_RELATION_TAG s where s.tag_id in ?1", nativeQuery = true)
    List<HotelRelationTag> findHotelIdsByTagIds(List<String> tagIds);


}
