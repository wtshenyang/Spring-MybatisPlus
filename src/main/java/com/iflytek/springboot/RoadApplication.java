package com.iflytek.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动入口
 * ServletComponentScan 过滤器注解
 * EnableCaching   缓存注解
 * MapperScan 扫描DAO
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
@EnableCaching
@MapperScan("com.iflytek.**.dao")
public class RoadApplication {


    public static void main(String[] args) {
        //默认启动
        SpringApplication.run(RoadApplication.class, args);
    }
}
