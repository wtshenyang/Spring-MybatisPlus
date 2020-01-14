package com.iflytek.springboot.base.plugins;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * springboot集成分页插件
 */
@Configuration
public class MyBatisConfiguration {

    @Bean
    public PageInterceptor sqlStatsInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "oracle");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
