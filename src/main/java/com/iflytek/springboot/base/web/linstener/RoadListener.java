package com.iflytek.springboot.base.web.linstener;

import com.iflytek.springboot.base.utils.EhcacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * 监听器
 *
 * @author GuFeng
 * @version 1.0
 * @since 2017/6/11 下午11:34
 */
@WebListener
public class RoadListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //添加缓存
        EhcacheUtil.getInstance().put("ehcacheGO", "username", "shenyang");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
