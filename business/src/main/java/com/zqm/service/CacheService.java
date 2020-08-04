package com.zqm.service;

/**
 * @describe:
 * @author:zqm
 */
public interface CacheService {

    String getString();

    String cleanString();

    String getRedisString();

    String cleanRedisString();
}
