package com.tianjian.service.impl;

import com.tianjian.data.bean.HotelRelationTag;
import com.tianjian.data.bean.TagDO;
import com.tianjian.data.service.HotelRelationTagCurd;
import com.tianjian.data.service.TagCurd;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.ServiceEnum;
import com.tianjian.service.TagManagerService;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tianjian on 2019/1/12.
 */
@Service
public class TagManagerServiceImpl implements TagManagerService {
    private Logger logger = LoggerFactory.getLogger(TagManagerServiceImpl.class);

    @Autowired
    TagCurd tagCurd;

    @Autowired
    HotelRelationTagCurd hotelRelationTagCurd;

    /**
     * 获取所有标签记录
     * @return 标签记录
     */
    @Override
    public ServiceMessage<List<TagDO>> getTagDO() {
        return new ServiceMessage<List<TagDO>>(ServiceEnum.SUCCESS, tagCurd.findAll());
    }

    /**
     * 获取酒店标签信息
     * @param hotelId 酒店ID
     * @return 酒店的标签列表
     */
    @Override
    public ServiceMessage<List<TagDO>> getTagDOByHotelId(String hotelId) {
        List<HotelRelationTag> relationTags = hotelRelationTagCurd.findByHotelIdOrderByDateDesc(hotelId);
        List<String> tagIds = new ArrayList<>();
        for(HotelRelationTag relation : relationTags) {
            tagIds.add(relation.getTagId());
        }
        List<TagDO> allTags = tagCurd.getTagByIds(tagIds);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, allTags);
    }

    @Override
    public ServiceMessage<List<HotelRelationTag>> getHotelRelationTag(List<String> tags) {
        List<HotelRelationTag> datas = hotelRelationTagCurd.findHotelIdsByTagIds(tags);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, datas);
    }

    /**
     * 根据标签ID删除标签
     * @param tagId 标签ID
     * @return 业务封装对象
     */
    @Override
    public ServiceMessage<Boolean> deleteTagDOByTagId(String tagId) {

        /**
         * 清除tag关联信息
         */
        List<HotelRelationTag> relations = hotelRelationTagCurd.findByTagIdOrderByDateDesc(tagId);
        if(relations != null && relations.size() > 0) {
            hotelRelationTagCurd.deleteByTagId(tagId);
        }

        if(!tagCurd.findById(tagId).isPresent()) {
            logger.warn("can not find hotel tag realtion tagId", tagId);
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }

        tagCurd.deleteById(tagId);

        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    /**
     * 删除关联关系对象
     * @param relationId 关联关系ID
     * @return 业务封装对象
     */
    @Override
    public ServiceMessage<Boolean> deleteHotelRelationTag(String relationId) {
        hotelRelationTagCurd.deleteById(relationId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    /**
     * 保存标签信息
     * @param tagDO 标签数据
     * @return 业务封装
     */
    @Override
    public ServiceMessage saveTagDO(TagDO tagDO) {
        if(StringUtils.isBlank(tagDO.getTagId())) {
            tagDO.setTagId(UUIDUtil.getPreUUID("TAG"));
        }
        tagDO.setDate(new Date());
        tagCurd.save(tagDO);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }

    /**
     * 保存酒店标签关系对象
     * @param hotelRelationTags 酒店标签关系对象
     * @return 业务封装对象
     */
    @Override
    public ServiceMessage saveHotelRealtionTag(List<HotelRelationTag> hotelRelationTags) {

        for(HotelRelationTag hotelRelationTag : hotelRelationTags) {
            if(StringUtils.isBlank(hotelRelationTag.getRelationId())) {
                hotelRelationTag.setRelationId(UUIDUtil.getPreUUID("RELATION:TAG-HOTEL"));
            }
            hotelRelationTag.setDate(new Date());
            hotelRelationTagCurd.save(hotelRelationTag);
        }

        return new ServiceMessage(ServiceEnum.SUCCESS, null);
    }
}
