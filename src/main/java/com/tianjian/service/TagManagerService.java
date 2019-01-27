package com.tianjian.service;

import com.tianjian.data.bean.HotelRelationTag;
import com.tianjian.data.bean.TagDO;
import com.tianjian.model.ServiceMessage;

import java.util.List;

/**
 * Created by tianjian on 2019/1/12.
 */
public interface TagManagerService {

    ServiceMessage<List<TagDO>> getTagDO();

    ServiceMessage<List<TagDO>> getTagDOByHotelId(String hotelId);

    ServiceMessage<Boolean> deleteTagDOByTagId(String tagId);

    ServiceMessage<Boolean> deleteHotelRelationTag(String relationId);

    ServiceMessage saveTagDO(TagDO tagDO);

    ServiceMessage saveHotelRealtionTag(List<HotelRelationTag> hotelRelationTags);

    ServiceMessage<List<HotelRelationTag>> getHotelRelationTag(List<String> tags);
}
