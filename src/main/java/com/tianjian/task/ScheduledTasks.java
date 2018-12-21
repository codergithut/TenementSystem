package com.tianjian.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tianjian on 2018/12/2.
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    @Async
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        System.out.println("The time is now {}" + dateFormat.format(new Date()));
    }

    public final static long ONE_Minute =  60 * 1000;

    @Scheduled(fixedDelay=ONE_Minute)
    @Async
    public void fixedDelayJob(){
        System.out.println(" >>fixedDelay执行....");
    }

    @Scheduled(fixedRate=ONE_Minute)
    @Async
    public void fixedRateJob(){
        System.out.println(" >>fixedRate执行....");
    }

    @Scheduled(cron="0 15 3 * * ?")
    @Async
    public void cronJob(){
        System.out.println((new Date())+" >>cron执行....");
    }

}
