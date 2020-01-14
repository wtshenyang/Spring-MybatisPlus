package com.iflytek.springboot.base.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


/**
 * Created by zzx.
 */
public final class SpringUtil {
    /**
     * 根据beanId在spring容器中获取对象
     *
     * @param beanId spring容器中的beanId
     * @return 获取的bean实例
     */
    public static Object getBeanFromContext(String beanId) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        return context.getBean(beanId);
    }


}