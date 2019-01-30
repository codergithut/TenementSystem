package com.tianjian.service.impl;

import com.tianjian.data.bean.RealtionFile;
import com.tianjian.data.bean.RoomDO;
import com.tianjian.data.service.RealtionFileDao;
import com.tianjian.data.service.RoomCurd;
import com.tianjian.model.RoomDetail;
import com.tianjian.model.ServiceMessage;
import com.tianjian.service.RoomManagerService;
import com.tianjian.service.ServiceEnum;
import com.tianjian.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 房间管理业务类
 * Created by tianjian on 2019/1/12.
 */
@Service
public class RoomManagerServiceImpl implements RoomManagerService {

    private Logger logger = LoggerFactory.getLogger(HotelManagerServiceImpl.class);

    @Autowired
    RoomCurd roomCurd;

    @Autowired
    RealtionFileDao realtionFileDao;

    @Value("${images.path}")
    private String imagePath;

    /**
     * 根据酒店ID获取房间信息
     * @param hotelId 酒店ID
     * @return 房间信息列表
     */
    @Override
    public ServiceMessage<List<RoomDO>> findRoomInfoByHotelId(String hotelId) {
        List<RoomDO> roomDOS = roomCurd.findByHotelIdOrderByDateDesc(hotelId);
        return new ServiceMessage<List<RoomDO>>(ServiceEnum.SUCCESS, roomDOS);
    }

    /**
     *
     * @param roomId
     * @return
     */
    @Override
    public ServiceMessage<Boolean> deleteRoomByRoomId(String roomId) {
        Optional<RoomDO> data = roomCurd.findById(roomId);
        if(!data.isPresent()) {
            logger.info("can not find room by roomId={}", roomId);
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }
        roomCurd.deleteById(roomId);
        return new ServiceMessage<>(ServiceEnum.SUCCESS, true);
    }

    /**
     *
     * @param roomDO
     * @return
     */
    @Override
    public ServiceMessage<RoomDO> addRoomInfo(RoomDO roomDO) {
        if(StringUtils.isBlank(roomDO.getRoomId())) {
            roomDO.setRoomId(UUIDUtil.getPreUUID("ROOM"));
        }
        roomDO.setDate(new Date());
        RoomDO rommDo = roomCurd.save(roomDO);
        return new ServiceMessage<RoomDO>(ServiceEnum.SUCCESS, rommDo);
    }

    /**
     *
     * @param hotelId
     * @return
     */
    @Override
    public ServiceMessage<Boolean> deleteRoomByHotelId(String hotelId) {
        List<RoomDO>  datas = roomCurd.findByHotelIdOrderByDateDesc(hotelId);
        if(datas == null && datas.size() == 0) {
            logger.warn("can not find room by hotelId=",hotelId);
            return new ServiceMessage(ServiceEnum.FAIL_FIND_RECORD,null);
        }
        roomCurd.deleteByHotelId(hotelId);
        return new ServiceMessage<Boolean>(ServiceEnum.SUCCESS, true);
    }

    /**
     *
     * @param roomId
     * @return
     */
    @Override
    public ServiceMessage<RoomDetail> findRoomInfoByRoomId(String roomId) {
        RoomDetail roomDetail = new RoomDetail();
        Optional<RoomDO> roomDO = roomCurd.findById(roomId);
        if(roomDO.isPresent()) {
            roomDetail.setRoomDO(roomDO.get());
        }

        List<RealtionFile> realtionFiles = realtionFileDao.findByRealtionIdOrderByDateDesc(roomId);

        if(realtionFiles != null && realtionFiles.size() > 0 ) {
            logger.warn("can not find room by roomId=",roomId);
            roomDetail.setPicUrl(imagePath + realtionFiles.get(0).getResourceCode());
        }

        return new ServiceMessage<RoomDetail>(ServiceEnum.SUCCESS, roomDetail);
    }
}
