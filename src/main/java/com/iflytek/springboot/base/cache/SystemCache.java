package com.iflytek.springboot.base.cache;

import com.iflytek.springboot.base.utils.ValidateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统缓存加载类
 *
 * @author leiding
 */
public class SystemCache {
    private static Map<String, Object> cacheSystemData = new HashMap<String, Object>();
    /**
     * 需要在系统启动时运行的SystemCacheService实现类
     */
    private List<String> loadClass;

    public SystemCache() {

    }

    /**
     * 要加载的BEAN ID名称
     *
     * @param loadClass
     */
    public void setLoadClass(List<String> loadClass) {
        this.loadClass = loadClass;
    }

    public List<String> getLoadClass() {
        return this.loadClass;
    }

    public static void setCacheSystemData(String key, Object value) {
        if (ValidateUtil.isNotEmpty(key)) {
            cacheSystemData.put(key, value);
        }
    }

    public static Object getCacheSystemData(String key) {
        if (ValidateUtil.isEmpty(key)) {
            return null;
        }
        return cacheSystemData.get(key);
    }
}