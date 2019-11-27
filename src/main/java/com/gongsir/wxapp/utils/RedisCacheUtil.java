package com.gongsir.wxapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author gongsir
 */
@Component
public class RedisCacheUtil {
    public static RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisCacheUtil.redisTemplate = redisTemplate;
    }
}
