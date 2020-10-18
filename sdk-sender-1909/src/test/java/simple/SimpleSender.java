package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author 徒有琴
 */
public class SimpleSender {
    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 100; i++) {
            String message = "hello " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());//发消息
        }
        System.out.println("发送成功");
        channel.close();
        connection.close();
    }
}
