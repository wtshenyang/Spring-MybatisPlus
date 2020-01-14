package com.iflytek.springboot.base.cache;

/**
 * 系统缓存实现接口，该接口在WEB容器启动时可以自动加载缓存
 * @author leiding
 */
public interface SystemCacheService {

	/**
	 * 第一次WEB容器启动时加载缓存运行的方法。 建议用“synchronized”方法
	 */
	void run();
	/**
	 * 更新缓存的方法
	 */
	void update();
	/**
	 * 当前缓存模块名称
	 * 
	 * @return
	 */
	String getCacheName();

}