package com.iflytek.springboot.base.datasource;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ManagedDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataBase1Properties dataBase1Properties;

    @Autowired
    private DataBase2Properties dataBase2Properties;

    @Bean(name = "dataBase1DataSource")
    public DruidAbstractDataSource dataBase1DataSource(){
        logger.info("dataBase1DataSource初始化----连接mysql成功");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataBase1Properties.getUrl());
        dataSource.setUsername(dataBase1Properties.getUsername());
        dataSource.setPassword(dataBase1Properties.getPassword());
        dataSource.setDriverClassName(dataBase1Properties.getDriverClassName());
        return dataSource;
    }

    @Bean(name = "dataBase2DataSource")
    @Primary
    public DruidAbstractDataSource dataBase2DataSource(){
        logger.info("dataBase2DataSource初始化----连接oracle成功");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataBase2Properties.getUrl());
        dataSource.setUsername(dataBase2Properties.getUsername());
        dataSource.setPassword(dataBase2Properties.getPassword());
        dataSource.setDriverClassName(dataBase2Properties.getDriverClassName());
        return dataSource;
    }




}
