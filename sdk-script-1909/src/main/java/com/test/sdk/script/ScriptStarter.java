package com.test.sdk.script;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 徒有琴
 */
@SpringBootApplication
@MapperScan(basePackages = "com.test.sdk.script.dao")
public class ScriptStarter {
    public static void main(String[] args) {
        SpringApplication.run(ScriptStarter.class, args);
    }
}
