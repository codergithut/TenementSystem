package com.tianjian.rest;

import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.service.HotelCurd;
import com.tianjian.exception.PageInfoError;
import com.tianjian.model.HotelDetail;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.HotelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 酒店接口
 * Created by tianjian on 2018/12/23.
 */
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelManagerService hotelManagerService;

    @Autowired
    private HotelCurd hotelCurd;

    /**
     * 根据酒店id获取酒店详细信息，包括房间信息
     * @param hotelId 酒店ID
     * @return 酒店详情
     */
    @GetMapping("/detail")
    public ResponseData<HotelDetail> searchHotelDetail(String hotelId) {
        ResponseData<HotelDetail> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.getHotelDetail(hotelId));
    }


    /**
     * 添加酒店信息
     * @param hotelDetail 酒店基础数据
     * @return 酒店信息
     */
    @PostMapping("/add")
    public ResponseData<HotelDO> saveOrUpdate(@RequestBody HotelDetail hotelDetail) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.saveHotelDO(hotelDetail));
    }

    /**
     * 删除酒店信息
     * @param hotelId 酒店ID
     * @return 业务返回封装信息
     */
    @GetMapping("/delete")
    public ResponseData deleteHotel(@RequestParam(value="hotelId",required=true)
            String hotelId) {
        ResponseData<HotelDO> responseData = new ResponseData<>();
        return responseData.buildResponseDataByCode(hotelManagerService.deleteHotelDO(hotelId));
    }

    /**
     * 分页查询酒店信息
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 分页大小
     * @return 分页返回数据
     * @throws PageInfoError 分页对象不合理
     */
    @GetMapping("/search")
    public ResponseData<Page<HotelDO>> searchByUserId
            (@RequestParam(value="userId",required=true) String userId,
             @RequestParam(value="page",required=true) int page,
             @RequestParam(value="pageSize",required=true) int pageSize) throws PageInfoError {
        ResponseData<Page<HotelDO>> responseData = new ResponseData<>();
        if(page < 0 || pageSize < 0) {
            throw new PageInfoError();
        }
        PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "date");
        return responseData.buildResponseDataByCode(hotelManagerService.findHotelDO(userId, pageRequest));
    }


    //todo delete test
    /**
     * 测试方法，为方便查看接口数据
     * @return 返回接口数据
     */
    @GetMapping("/findALL")
    public List<HotelDO> findAllHotel() {
        return hotelCurd.findAll();
    }

    @GetMapping("/getHotelByTag")
    public ResponseData<HotelDO> getHotelByTags() {
        return null;
    }

}
