package com.shawn.chche;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;


public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisCache redisCache;

    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}