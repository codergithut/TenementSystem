package com.tianjian.data.service;

import com.tianjian.data.bean.HotelRelationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tianjian on 2018/12/31.
 * 酒店信息添删改查
 */
@Repository
public interface HotelRelationUserCurd
        extends JpaRepository<HotelRelationUser, String> {

    /**
     * 根据用户id获取用户关系信息
     * @param userId 用户ID
     * @return 关系信息
     */
    List<HotelRelationUser> findByUserIdOrderByDateDesc(String userId);

    /**
     * 根据HotelId获取关系信息
     * @param hotelId 酒店ID
     * @return 关系信息
     */
    List<HotelRelationUser> findByHotelIdOrderByDateDesc(String hotelId);

    /**
     * 根据酒店ID删除关系信息
     * @param hotelId 酒店ID
     * @return 删除条目
     */
    @Transactional
    Integer deleteByHotelId(String hotelId);

    /**
     * 根据用户ID删除关系信息
     * @param userId 用户ID
     * @return 删除条数
     */
    @Transactional
    Integer deleteByUserId(String userId);
}
