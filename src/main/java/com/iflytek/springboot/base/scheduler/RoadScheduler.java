package com.iflytek.springboot.base.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * 定时任务
 *
 * @author GuFeng
 * @version 1.0
 * @since 2017/6/11 下午11:41
 */
@Configuration
@EnableScheduling
public class RoadScheduler {

    private Logger logger = LoggerFactory.getLogger(getClass());

 /*   @Scheduled(cron = "0/2 * * * * ?")//每隔2秒执行一次
    public void schedule() {
       logger.info("定时任务");
    }*/

}
