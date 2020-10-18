package com.test.sdk.log.receiver;

import com.rabbitmq.client.Channel;
import com.test.sdk.common.logs.LoginServerLog;
import com.test.sdk.common.util.JsonUtil;
import com.test.sdk.log.dao.LoginServerLogDAO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 徒有琴
 */
@Component
@RabbitListener(queues = "login_server_log")//监听test队列
public class MessageListener {
    @Autowired
    private LoginServerLogDAO loginServerLogDAO;

    @RabbitHandler//标记当前方法是处理消息的
    public void receive(String key, Channel channel, Message message) {
        String json = new String(message.getBody());
        System.out.println(json);
        try {
            LoginServerLog log=(LoginServerLog) JsonUtil.getObj(json,LoginServerLog.class);
            loginServerLogDAO.insertLog(log);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
