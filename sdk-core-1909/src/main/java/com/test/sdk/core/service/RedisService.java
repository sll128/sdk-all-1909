package com.test.sdk.core.service;

import com.test.sdk.common.util.ResponseTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SDK-REDIS")
public interface RedisService {
    @RequestMapping("/set")
    ResponseTO set(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping("/get")
    ResponseTO get(@RequestParam("key") String key);

    @RequestMapping("/setex")
    ResponseTO setex(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") Integer time);

    @RequestMapping("/del")
    ResponseTO del(@RequestParam("key") String key);

    @RequestMapping("/expire")
    ResponseTO expire(@RequestParam("key") String key, @RequestParam("time") Integer time);
}
