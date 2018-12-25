package com.tianjian;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.model.bean.UserDO;
import com.tianjian.data.impl.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by tianjian on 2018/11/29.
 * 测试 hsql 数据库使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.tianjian.Example.class)
public class UserDOTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void before() {
        UserDO userDO = new UserDO();
        userDO.setId("ss");
        userDO.setName("风清扬");
        userDO.setAccount("fengqy");
        userDO.setPwd("123456");
        userDao.save(userDO);
        userDO = new UserDO();
        userDO.setId("221");
        userDO.setName("东方不败");
        userDO.setAccount("bubai");
        userDO.setPwd("123456");
        userDao.save(userDO);
        userDO.setId("333s");
        userDO.setName("向问天");
        userDO.setAccount("wentian");
        userDO.setPwd("123456");
        userDao.save(userDO);

        System.out.println(JSONObject.toJSONString(userDO));
    }
    @Test
    public void testAdd() {
        List<UserDO> userDOList = userDao.findAll();
        for(UserDO userDO : userDOList) {
            System.out.println(userDO.getName());
        }
    }

    @After
    public void after() {
//        userDao.deleteById(1L);
//        userDao.deleteById(3L);
//        userDao.deleteById(5L);
    }

}
