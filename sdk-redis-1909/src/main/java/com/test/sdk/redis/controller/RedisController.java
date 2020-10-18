package com.test.sdk.redis.controller;

import com.test.sdk.common.util.ResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author 徒有琴
 */
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/set")
    public ResponseTO set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return ResponseTO.ok("ok");
    }

    @RequestMapping("/get")
    public ResponseTO get(String key) {
        return ResponseTO.ok(redisTemplate.opsForValue().get(key));
    }

    @RequestMapping("/setex")
    public ResponseTO setex(String key, String value, Integer time) {
        //同时设置过期时间
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        return ResponseTO.ok("ok");
    }

    @RequestMapping("/del")
    public ResponseTO del(String key) {
        redisTemplate.delete(key);
        return ResponseTO.ok("ok");
    }

    @RequestMapping("/expire")
    public ResponseTO expire(String key, Integer time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return ResponseTO.ok("ok");
    }
}
