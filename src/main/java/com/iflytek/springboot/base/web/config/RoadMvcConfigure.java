package com.iflytek.springboot.base.web.config;

import com.iflytek.springboot.base.web.interceptor.RoadInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * MVC配置
 *
 * @author GuFeng
 * @version 1.0
 * @since 2017/6/11 下午11:25
 */
@Configuration
public class RoadMvcConfigure extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(new RoadInterceptor()).addPathPatterns("/**");//.excludePathPatterns()
        registry.addInterceptor(new RoadInterceptor()).addPathPatterns("/**");//.excludePathPatterns()
        super.addInterceptors(registry);
    }
}
