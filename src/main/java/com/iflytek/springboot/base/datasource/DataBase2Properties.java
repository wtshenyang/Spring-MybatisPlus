package com.iflytek.springboot.base.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 多数据源配置 2
 */
/*@ConfigurationProperties(prefix = "spring.datasource.database2")*/
@ConfigurationProperties(prefix = "database2")
@Component
@Data
@PropertySource("classpath:config.properties")
public class DataBase2Properties {


    private String url;

    private String username;

    private String password;

    private String driverClassName;
}
