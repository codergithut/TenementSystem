package com.tianjian;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.CommentDO;
import com.tianjian.data.bean.HotelDO;
import com.tianjian.data.bean.HotelRelationUser;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.CommentCurd;
import com.tianjian.data.service.HotelCurd;
import com.tianjian.data.service.UserCurd;
import com.tianjian.util.UUIDUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2018/11/29.
 * 测试 hsql 数据库使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.tianjian.Example.class)
public class CurdDoTest {

    @Autowired
    private UserCurd userDao;

    @Autowired
    private HotelCurd hotelDao;

    @Autowired
    CommentCurd commentCurd;

    @Before
    public void before() {
        UserDO userDO = new UserDO();
        userDO.setUserId("ss");
        userDO.setEmail("风清扬");
        userDO.setAccount("fengqy");
        userDO.setPassword("123456");
        userDO.setRole("USER");
        userDao.save(userDO);

        HotelDO hotelDO = new HotelDO();
        hotelDO.setAlias("测试账号");
        hotelDO.setContent("很多消息");
        hotelDO.setName("测试酒店");
        hotelDO.setHotelId(UUID.randomUUID().toString());
        hotelDO.setImg("http://www.baidu.com");
        hotelDO.setLocation("SH");
        hotelDao.save(hotelDO);
    }
    @Test
    public void testAdd() {

        List<CommentDO> commentDOS = commentCurd.findAll();
        for(CommentDO hotelDO : commentDOS) {
            System.out.println(hotelDO.getUserId());
        }
    }

    //@After
    public void after() {
//        userDao.deleteById(1L);
//        userDao.deleteById(3L);
//        userDao.deleteById(5L);
    }



}
