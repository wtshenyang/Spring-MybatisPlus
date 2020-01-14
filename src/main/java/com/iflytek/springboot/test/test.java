package com.iflytek.springboot.test;


import com.GeneratorTool;
import com.jdbc.DataBaseProperties;

import java.io.File;

public class test {
    public static void main(String[] args) throws Exception {

        /*设置链接*/
        DataBaseProperties dataBase=new DataBaseProperties();
        dataBase.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataBase.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        dataBase.setUsername("XY_APP");
        dataBase.setPassword("XY_APP");

        /*设置page*/
        dataBase.setPageUtilPath("com.iflytek.springboot.base.tags.PageUtil");
        dataBase.setPageVoPath("com.iflytek.springboot.base.tags.PageVo");
        dataBase.setResultDOPath("com.iflytek.springboot.base.tags.ResultDO");
        dataBase.setSysCodePath("com.iflytek.springboot.base.utils.SysCode");

        /*设置mapper地址*/
        dataBase.setMapperPackage("mapper");
        dataBase.setMapperAddress("D:\\work\\Springboot-mybatisplus\\src\\main\\resources\\");

        /*设置pojo地址*/
        dataBase.setPojoAddress("D:\\work\\Springboot-mybatisplus\\src\\main\\java\\");
        dataBase.setPojoPackage("com.iflytek.springboot.pojo");

        /*设置dao地址*/
        dataBase.setDaoAddress("D:\\work\\Springboot-mybatisplus\\src\\main\\java\\");
        dataBase.setDaoPackage("com.iflytek.springboot.dao");


        /*设置service地址*/
        dataBase.setServiceAddress("D:\\work\\Springboot-mybatisplus\\src\\main\\java\\");
        dataBase.setServiecePackage("com.iflytek.springboot.service");

        /*设置serviceImpl地址*/
        dataBase.setServiceImplAddress("D:\\work\\Springboot-mybatisplus\\src\\main\\java\\");
        dataBase.setServieceImplPackage("com.iflytek.springboot.service.serviceImpl");

        /*设置controller地址*/
        dataBase.setControllerAddress("D:\\work\\Springboot-mybatisplus\\src\\main\\java\\");
        dataBase.setControllerMackage("com.iflytek.springboot.controller");

        /*表名*/
        dataBase.setTablesName("t_uaac_organization");

        GeneratorTool.createFile(null,dataBase);
    }
}
