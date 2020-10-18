package com.test.sdk.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 徒有琴
 */
@SpringBootApplication
@MapperScan(basePackages = "com.test.sdk.log.dao")
public class LogStarter {
    public static void main(String[] args) {
        SpringApplication.run(LogStarter.class,args);
    }

    //队列
    @Bean
    public Queue test1() {//方法名是bean的id
        Queue queue = new Queue("login_server_log");
        return queue;
    }

    //工厂
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("10.9.45.155");
        factory.setPort(5672);
        factory.setVirtualHost("/test");
        factory.setUsername("luke");
        factory.setPassword("luke");
        factory.setPublisherReturns(true);
        factory.setPublisherConfirms(true);
        return factory;
    }
}
