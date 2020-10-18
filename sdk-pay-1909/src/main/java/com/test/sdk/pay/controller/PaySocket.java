package com.test.sdk.pay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 徒有琴
 */
@ServerEndpoint("/call/{orderNum}")
@Component
public class PaySocket {
    private static Map<String, Session> clients = new HashMap<>();

    @OnOpen//此注解的作用是声明当前方法是当建立连接的时候调用
    public void onOpen(@PathParam("orderNum") String orderNum, Session session) {
        System.out.println("有连接" + session);
        System.out.println(orderNum);
        clients.put(orderNum, session);
    }

    @OnMessage//此注解的作用是当收到消息的时候执行
    public void onMessage(Session session, String message) {
    }

    @OnClose//此注解的作用是 当连接关闭的时候执行
    public void onClose(Session session) {
        System.out.println(session + "关闭连接");
    }

    @OnError//此注解的作用是当出现异常的时候执行
    public void onError(Session session, Throwable e) {
        try {
            e.printStackTrace();
            session.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static  void sendMsg(String orderNum, String message) {
        Session session = clients.get(orderNum);
        if (session != null) {
            session.getAsyncRemote().sendText(message);

        }
    }
}
