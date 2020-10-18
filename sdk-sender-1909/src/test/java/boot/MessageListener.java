package boot;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 徒有琴
 */
@Component
@RabbitListener(queues = "test1")//监听test队列
public class MessageListener {
    @RabbitHandler//标记当前方法是处理消息的
    public void receive(String key, Channel channel, Message message) {
        String str = new String(message.getBody());
        System.out.println(str);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
