package com.tianjian.rest;

import com.tianjian.data.bean.HotelRelationTag;
import com.tianjian.data.bean.TagDO;
import com.tianjian.data.service.HotelRelationTagCurd;
import com.tianjian.data.service.TagCurd;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.TagManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签数据接口
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/api/tag")
public class TagsController {

    @Autowired
    private TagManagerService tagManagerService;

    @Autowired
    private TagCurd tagCurd;

    @Autowired
    private HotelRelationTagCurd hotelRelationTagCurd;

    /**
     * 查询所有标签信息
     * @return 标签信息列表
     */
    @GetMapping("/search")
    public ResponseData<List<TagDO>> searchHotel() {
        ResponseData<List<TagDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.getTagDO());
    }

    /**
     * 根据酒店ID后去标签信息
     * @param hotelId 酒店ID
     * @return 标签列表
     */
    @GetMapping("/searchByHotelId")
    public ResponseData<List<TagDO>> searchBuHotelId(String hotelId) {
        ResponseData<List<TagDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.getTagDOByHotelId(hotelId));
    }

    /**
     * 根据酒店ID后去标签信息
     * @return 标签列表
     */
    @GetMapping("/searchHotelIdsByTagsIds")
    public ResponseData<List<HotelRelationTag>> searchHotelIdsByTagsIds(List<String> tagsIds) {
        ResponseData<List<HotelRelationTag>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.getHotelRelationTag(tagsIds));
    }

    /**
     * 添加标签信息
     * @param tagDO 标签信息
     * @return 业务封装
     */
    @PostMapping("/add")
    public ResponseData<Boolean> addTag(@RequestBody TagDO tagDO) {
        ResponseData<HotelDetail> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.saveTagDO(tagDO));
    }

    /**
     * 根据标签ID删除标签
     * @param tagId 标签ID
     * @return 业务封装
     */
    @GetMapping("/delete")
    public ResponseData<Boolean> deleteTag(String tagId) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.deleteTagDOByTagId(tagId));
    }

    /**
     * 添加酒店标签关系数据
     * @param hotelRelationTag 酒店标签关系实体数据
     * @return 业务封装数据
     */
    @PostMapping("/relation/add")
    public ResponseData<Boolean> addRelationTag(@RequestBody HotelRelationTag hotelRelationTag) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.saveHotelRealtionTag(hotelRelationTag));
    }

    /**
     * 删除酒店标签关系数据
     * @param relationId 酒店标签关系数据ID
     * @return 业务封装数据
     */
    @GetMapping("/relation/delete")
    public ResponseData<Boolean> deleteRelationTag(String relationId) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.deleteHotelRelationTag(relationId));
    }

    //todo delete test

    /**
     * 获取所有关系数据
     * @return 关系数据列表
     */
    @GetMapping("/relation/all")
    public List<HotelRelationTag> findAllHotel() {
        return hotelRelationTagCurd.findAll();
    }

    //todo delete test

    /**
     * 获取所有标签信息
     * @return 标签信息列表
     */
    @GetMapping("/all")
    public List<TagDO> findAllHotel1() {
        return tagCurd.findAll();
    }

}
