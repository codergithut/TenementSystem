package com.tianjian.init;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.UserCurd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.tianjian.config.Constract.HOTELADMIN;

/**
 * Created by tianjian on 2019/1/20.
 */

/**
 * 初始化系统消息
 */
@Order(2)
@Component
public class ServerStartedReport implements CommandLineRunner{

    private Logger logger = LoggerFactory.getLogger(ServerStartedReport.class);

    private static final String ROOTID = "ROOT";


    @Autowired
    UserCurd userCurd;

    @Override
    public void run(String... args) throws Exception {
        List<UserDO> userDOList = userCurd.findAll();

        if(userDOList != null && userDOList.size() > 0) {
            for(UserDO user : userDOList) {
                if(HOTELADMIN.equals(user.getRole())) {
                    logger.info("system hava HOTELADMIN role");
                    return ;
                }
            }
        }

        UserDO user = new UserDO();
        user.setDate(new Date());
        user.setRole(HOTELADMIN);
        user.setUserId(ROOTID);
        user.setAccount("ROOT");
        user.setEmail("com.example@163.com");
        user.setPassword("ROOT");
        user.setUsername("ROOT");
        user.setOperatingId(ROOTID);
        userCurd.save(user);
        System.out.println("===========ServerStartedReport启动====="+ LocalDateTime.now());
    }
}

