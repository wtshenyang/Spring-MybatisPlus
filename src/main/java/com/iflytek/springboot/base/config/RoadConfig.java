package com.iflytek.springboot.base.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 *
 * 自定义配置读取
 * @version 1.0
 * Configuration 自动扫描
 * ConfigurationProperties 配置注解 prefix 前缀
 */
@Configuration
@ConfigurationProperties("road")
@PropertySource("classpath:config.properties")
public class RoadConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    @PostConstruct
    public void init() {
        logger.info("RoadConfig-1:" + this.getName());
        logger.info("RoadConfig-2:" + this.getAge());
    }

}
