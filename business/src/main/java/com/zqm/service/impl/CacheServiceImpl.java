package com.zqm.service.impl;

import com.zqm.service.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @describe:
 * @author:zqm
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Cacheable(cacheNames = "getString", key = "'d'")
    public String getString() {
        return "aaa";
    }

    @Override
    @CacheEvict(value = "getString", key = "'d'")
    public String cleanString() {
        return "bb";
    }

    @Override
    public String getRedisString() {
        ValueOperations<String, String> redisString = redisTemplate.opsForValue();
        // SET key value: 设置指定 key 的值
        if (StringUtils.isBlank(redisString.get("strKey1"))) {
            //设置缓存
            redisString.set("strKey1", "hello spring boot redis");
            redisString.set("strKey2", "hello spring boot redis");

            //设置缓存时间 等价 redisString.set("strKey1","hello spring boot redis",60,TimeUnit.SECONDS);
            redisTemplate.expire("strKey1",60, TimeUnit.SECONDS);
            return "没有缓存数据";
        }
        return redisString.get("strKey1");
    }

    @Override
    public String cleanRedisString() {
        redisTemplate.delete("strKey1");
        return "清楚缓存成功";
    }
}
