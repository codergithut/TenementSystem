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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianjian on 2019/1/12.
 */
@Service
public class TagManagerServiceImpl implements TagManagerService {

    @Autowired
    TagCurd tagCurd;

    @Autowired
    HotelRelationTagCurd hotelRelationTagCurd;

    @Override
    public ServiceMessage<List<TagDO>> getTagDO() {
        return new ServiceMessage<List<TagDO>>(ServiceEnum.SUCCESS, tagCurd.findAll());
    }

    @Override
    public ServiceMessage<List<TagDO>> getTagDOByHotelId(String hotelId) {
        List<HotelRelationTag> relationTags = hotelRelationTagCurd.findByHotelId(hotelId);
        List<String> tagIds = new ArrayList<>();
        for(HotelRelationTag relation : relationTags) {
            tagIds.add(relation.getTagId());
        }
        List<TagDO> allTags = tagCurd.getTagByIds(tagIds);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, allTags);
    }

    @Override
    public ServiceMessage<Boolean> deleteTagDOByTagId(String tagId) {
        tagCurd.deleteById(tagId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    @Override
    public ServiceMessage<Boolean> deleteHotelRelationTag(String relationId) {
        hotelRelationTagCurd.deleteById(relationId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    @Override
    public ServiceMessage saveTagDO(TagDO tagDO) {
        if(StringUtils.isBlank(tagDO.getTagId())) {
            try {
                tagDO.setTagId(UUIDUtil.getPreUUID("TAG"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tagCurd.save(tagDO);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);
    }

    @Override
    public ServiceMessage saveHotelRealtionTag(HotelRelationTag hotelRelationTag) {
        if(StringUtils.isBlank(hotelRelationTag.getRelationId())) {
            try {
                hotelRelationTag.setRelationId(UUIDUtil.getPreUUID("RELATION:TAG-HOTEL"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        hotelRelationTagCurd.save(hotelRelationTag);
        return new ServiceMessage(ServiceEnum.SUCCESS, null);
    }
}
