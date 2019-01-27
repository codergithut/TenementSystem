package com.tianjian.service.impl;

import com.tianjian.data.bean.*;
import com.tianjian.data.service.*;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.service.TagManagerService;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.tianjian.config.Constract.HOTELADMIN;
import static com.tianjian.config.Constract.MANAGER;
import static com.tianjian.config.Constract.USER;

/**
 * 酒店管理业务
 * Created by tianjian on 2019/1/1.
 */
@Service
public class HotelManagerServiceImpl implements HotelManagerService {

    @Autowired
    HotelCurd hotelCurd;

    @Autowired
    HotelRelationUserCurd hotelRelationUserCurd;

    @Autowired
    HotelRelationTagCurd hotelRelationTagCurd;

    @Autowired
    TagCurd tagCurd;

    @Autowired
    RoomCurd roomCurd;

    @Autowired
    UserCurd userCurd;

    /**
     * 根据用户信息查找酒店信息
     * @param userId 用户id
     * @param pageable 分页信息
     * @return 酒店信息列表
     */
    @Override
    public ServiceMessage<Page<HotelDO>> findHotelDO(String userId, Pageable pageable) {
        Optional<UserDO> userDOP = userCurd.findById(userId);
        if(!userDOP.isPresent()) {
            return new ServiceMessage<>(ServiceEnum.NOT_FIND_NAME, null);
        }

        UserDO userDO = userDOP.get();

        if(USER.equals(userDO.getRole()) || HOTELADMIN.equals(userDO.getRole())) {
            return new ServiceMessage<Page<HotelDO>>(ServiceEnum.SUCCESS, hotelCurd.findAll(pageable));
        } else if(MANAGER.equals(userDO.getRole())){
            List<String> hotelIds = new ArrayList<String>();
            List<HotelRelationUser> relationUsers = hotelRelationUserCurd.findByUserIdOrderByDateDesc(userId);
            if(relationUsers != null && relationUsers.size() >0) {
                relationUsers.forEach(t -> {
                    hotelIds.add(t.getHotelId());
                });
                return new ServiceMessage<Page<HotelDO>>(ServiceEnum.SUCCESS, hotelCurd.getHotelByIds(hotelIds, pageable));
            }
        }
        return new ServiceMessage<>(ServiceEnum.SUCCESS, null);

    }

    /**
     * 保存酒店信息
     * @param hotelView 酒店信息基础数据
     * @return 业务信息封装
     */
    @Override
    public ServiceMessage<HotelDO> saveHotelDO(HotelDetail hotelView) {
        HotelDO hotelDO = hotelView.getHotelInfo();
        List<HotelRelationTag> hotelRelationTags = hotelView.getHotelRelationTags();
        if(StringUtils.isBlank(hotelDO.getHotelId())) {
            hotelDO.setHotelId(UUIDUtil.getPreUUID("HOTEL"));
        } else {
            hotelRelationTagCurd.deleteByHotelId(hotelDO.getHotelId());
        }

        hotelDO.setDate(new Date());
        HotelDO save = hotelCurd.save(hotelDO);

        for (HotelRelationTag hotelRelationTag : hotelRelationTags) {
            if(StringUtils.isBlank(hotelRelationTag.getRelationId())) {
                hotelRelationTag.setDate(new Date());
                hotelRelationTag.setHotelId(hotelDO.getHotelId());
                hotelRelationTag.setRelationId(UUIDUtil.getPreUUID("RELATION:TAG-HOTEL"));
            }
        }
        hotelRelationTagCurd.saveAll(hotelRelationTags);
        if(save != null) {
            return new ServiceMessage(ServiceEnum.SUCCESS,  save);
        } else {
            return new ServiceMessage(ServiceEnum.SAVE_NULL,  null);
        }

    }

    /**
     * 根据酒店ID删除酒店信息
     * @param hotelId 酒店ID
     * @return 业务封装信息
     */
    @Override
    @Transactional
    public ServiceMessage deleteHotelDO(String hotelId) {
        Optional<HotelDO> hotelDO = hotelCurd.findById(hotelId);
        if(!hotelDO.isPresent()) {
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }

        /**
         * 清理房间信息
         */
        List<RoomDO> rooms = roomCurd.findByHotelIdOrderByDateDesc(hotelId);
        if(rooms != null && rooms.size() > 0) {
            roomCurd.deleteByHotelId(hotelId);
        }

        /**
         * 清理房间用户关联关系信息
         */
        List<HotelRelationUser> users = hotelRelationUserCurd.findByHotelIdOrderByDateDesc(hotelId);
        if(users != null && users.size() > 0) {
            hotelRelationUserCurd.deleteByHotelId(hotelId);
        }

        /**
         * 清除酒店标签关系表
         */
        List<HotelRelationTag> tags = hotelRelationTagCurd.findByHotelIdOrderByDateDesc(hotelId);
        if(tags != null && tags.size() > 0) {
            hotelRelationTagCurd.deleteByHotelId(hotelId);
        }

        /**
         * 删除酒店信息
         */
        hotelCurd.deleteById(hotelId);
        return new ServiceMessage(ServiceEnum.SUCCESS,  null);
    }

    /**
     * 获取酒店详情信息
     * @param hotelId 酒店ID
     * @return 业务封装信息
     */
    @Override
    public ServiceMessage<HotelDetail> getHotelDetail(String hotelId) {
        HotelDetail hotelDetail = new HotelDetail();

        Optional<HotelDO> hotelDO = hotelCurd.findById(hotelId);
        if(!hotelDO.isPresent()) {
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }

        List<HotelRelationTag> hotelRelationTag = hotelRelationTagCurd.findByHotelIdOrderByDateDesc(hotelId);
        hotelDetail.setHotelInfo(hotelDO.get());
        hotelDetail.setHotelRelationTags(hotelRelationTag);

        List<String> tags = new ArrayList<>();
        if(!CollectionUtils.isEmpty(hotelRelationTag)) {
            for(HotelRelationTag hotelRelationTag1 : hotelRelationTag) {
                Optional<TagDO> tag = tagCurd.findById(hotelRelationTag1.getTagId());
                if(tag.isPresent()) {
                    tags.add(tag.get().getName());
                }
            }
        }
        if(tags.size() > 0) {
            hotelDetail.setTags(tags);
        }

        List<RoomDO> rooms = roomCurd.findByHotelIdOrderByDateDesc(hotelId);
        if(rooms != null && rooms.size() > 0) {
            hotelDetail.setRoomsInfo(rooms);
        }

        return new ServiceMessage(ServiceEnum.SUCCESS,  hotelDetail);
    }

    @Override
    public ServiceMessage<Page<HotelDO>> getHotelByTags(List<String> tagIds, Pageable pageable) {

        List<String> hotelIds = new ArrayList<String>();
        List<HotelRelationTag> hotelRelationTags = hotelRelationTagCurd.findHotelIdsByTagIds(tagIds);
        for(HotelRelationTag hotelRelationTag : hotelRelationTags) {
            hotelIds.add(hotelRelationTag.getTagId());
        }
        return new ServiceMessage<Page<HotelDO>>(ServiceEnum.SUCCESS, hotelCurd.getHotelByIds(hotelIds, pageable));
    }

}
