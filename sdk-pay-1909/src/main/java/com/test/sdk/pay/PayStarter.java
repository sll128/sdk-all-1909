package com.test.sdk.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author 徒有琴
 */
@SpringBootApplication
@EnableWebSocket
public class PayStarter {
    public static void main(String[] args) {
        SpringApplication.run(PayStarter.class, args);
    }
//整合websocket的对象
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
