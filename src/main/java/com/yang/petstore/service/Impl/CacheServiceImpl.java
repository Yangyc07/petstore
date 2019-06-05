package com.yang.petstore.service.Impl;

import com.google.common.cache.Cache;
import com.yang.petstore.service.CacheService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {

    private Cache<String,Object> commmonCache = null;

    @PostConstruct
    public void init() {
        commmonCache = com.google.common.cache.CacheBuilder.newBuilder()
                .initialCapacity(10)//出实容量
                .maximumSize(100)//设置缓存中最大可以存放100个key，超过100个按LRU移除
                .expireAfterWrite(60, TimeUnit.SECONDS).build();//设置写缓存后多少秒过期
    }

    @Override
    public void setCommonCache(String key, Object value) {
        commmonCache.put(key, value);
    }

    @Override
    public Object getFromCommonCache(String key) {
        return commmonCache.getIfPresent(key);
    }
}
