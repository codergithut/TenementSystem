package com.tianjian.rest;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationTag;
import com.tianjian.data.bean.TagDO;
import com.tianjian.data.service.HotelCurd;
import com.tianjian.data.service.HotelRelationTagCurd;
import com.tianjian.data.service.TagCurd;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.HotelManagerService;
import com.tianjian.service.TagManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
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

    @GetMapping("/search")
    public ResponseData<List<TagDO>> searchHotel() throws Exception {
        ResponseData<List<TagDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.getTagDO());
    }

    @GetMapping("/searchByHotelId")
    public ResponseData<List<TagDO>> searchBuHotelId(String hotelId) throws Exception {
        ResponseData<List<TagDO>> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.getTagDOByHotelId(hotelId));
    }

    @PostMapping("/add")
    public ResponseData<Boolean> addTag(@RequestBody TagDO tagDO) throws Exception {
        ResponseData<HotelDetail> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.saveTagDO(tagDO));
    }

    @GetMapping("/delete")
    public ResponseData<Boolean> deleteTag(String tagId) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.deleteTagDOByTagId(tagId));
    }

    @PostMapping("/relation/add")
    public ResponseData<Boolean> addRelationTag(@RequestBody HotelRelationTag hotelRelationTag) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.saveHotelRealtionTag(hotelRelationTag));
    }

    @GetMapping("/relation/delete")
    public ResponseData<Boolean> deleteRelationTag(String relationId) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(tagManagerService.deleteHotelRelationTag(relationId));
    }

    //todo delete test
    @GetMapping("/relation/all")
    public List<HotelRelationTag> findAllHotel() {
        return hotelRelationTagCurd.findAll();
    }

    //todo delete test
    @GetMapping("/all")
    public List<TagDO> findAllHotel1() {
        return tagCurd.findAll();
    }

    public static void main(String[] args) {
        /**
         *
         {"name":"test","tagId":"a6958320-1db5-4baa-a3d7-2b73bf2ddccd"}
         {"hotelId":"hotelId","relationId":"b8fab0ac-d426-4619-a2a0-fef2b68497b8","tagId":"test"}
         */

        TagDO tagDO = new TagDO();
        tagDO.setTagId(UUID.randomUUID().toString());
        tagDO.setName("test");

        HotelRelationTag hotelRelationTag = new HotelRelationTag();
        hotelRelationTag.setRelationId(UUID.randomUUID().toString());
        hotelRelationTag.setTagId("test");
        hotelRelationTag.setHotelId("hotelId");

        System.out.println(JSONObject.toJSONString(tagDO));
        System.out.println(JSONObject.toJSONString(hotelRelationTag));
    }

}
