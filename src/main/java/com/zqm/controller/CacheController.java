package com.zqm.controller;

import com.zqm.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @RequestMapping("get")
    public String testCache() {
        return cacheService.getString();
    }

    @RequestMapping("clean")
    public String cleanCache() {
       return cacheService.cleanString();
    }
    @RequestMapping("getRedis")
    public String getRedis(){
        return cacheService.getRedisString();
    }
    @RequestMapping("cleanRedis")
    public String cleanRedis(){
        return cacheService.cleanRedisString();
    }
}
