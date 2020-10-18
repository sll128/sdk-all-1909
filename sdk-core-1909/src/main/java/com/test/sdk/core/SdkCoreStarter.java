package com.test.sdk.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author 徒有琴
 */
@SpringBootApplication
@MapperScan(basePackages = "com.test.sdk.core.dao")
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagement//开启事务的
public class SdkCoreStarter {
    public static void main(String[] args) {
        SpringApplication.run(SdkCoreStarter.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private DataSource dataSource;

    private String transactionExecution = "execution(* com.test.sdk.core.service..*(..))";

    //SpringBoot默认是没有事务切面的，需要自己定义
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_SUPPORTS,ISOLATION_DEFAULT,readOnly");
        attributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("do*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(new DataSourceTransactionManager(dataSource), attributes);
        advisor.setAdvice(txAdvice);
        return advisor;
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
