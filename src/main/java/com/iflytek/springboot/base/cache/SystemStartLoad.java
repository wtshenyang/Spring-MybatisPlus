package com.iflytek.springboot.base.cache;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Web容器启动时加载的类
 *
 * @author leiding
 */
public class SystemStartLoad implements ServletContextListener {
    Logger logger = Logger.getLogger(this.getClass());

    private SystemCache systemCache;

    public SystemCache getSystemCache() {
        return systemCache;
    }

    public void setSystemCache(SystemCache systemCache) {
        this.systemCache = systemCache;
    }

    /**
     * Web容器结束时执行的方法
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        if (logger.isInfoEnabled()){
            logger.info("缓存加载类将销毁......");
        }
    }

    /**
     * WEB容器启动时执行的方法
     */
    @Override
    public synchronized void contextInitialized(ServletContextEvent arg0) {
        if (this.logger.isInfoEnabled()) {
            logger.info("启动缓存加载......");
            systemCache = (SystemCache) WebApplicationContextUtils
                    .getWebApplicationContext(arg0.getServletContext()).getBean(
                            "systemCache");
        }


        if (systemCache.getLoadClass() != null
                && !systemCache.getLoadClass().isEmpty()) {
            for (int i = 0; i < systemCache.getLoadClass().size(); i++) {
                SystemCacheService scs = (SystemCacheService) WebApplicationContextUtils
                        .getWebApplicationContext(arg0.getServletContext())
                        .getBean(systemCache.getLoadClass().get(i));
                scs.run();
            }
        }
        System.gc();
        if (logger.isInfoEnabled()) {
            logger.info("加载缓存完毕！");
        }
    }
}
