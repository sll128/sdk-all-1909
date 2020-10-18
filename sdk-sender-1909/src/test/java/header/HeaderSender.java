package header;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import simple.ConnectionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徒有琴
 */
public class HeaderSender {
    //direct指定路由key，key要完全匹配才能收到，竞争消费，一条消息只能被一个消费者消费
    //fanout不指定key,不需要匹配key,广播模式，一条消息只能被多个消费者消费，注意业务中不要重复处理（别重复给浩哥发钱）
    //topic指定路由key，模糊匹配的路由key，*是通配符
    //header匹配的是消息头中的properties属性，消费者指定的属性值必须和发送者发送时设定的属性是一样的，才能收到
    private static String exchange_name = "exchanhe_name_header";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchange_name, BuiltinExchangeType.HEADERS);
        Map<String, Object> header = new HashMap<>();
        header.put("haoge", "999");
        header.put("format", "pdf");
        String message = "hello head";
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().headers(header).build();
        channel.basicPublish(exchange_name, "", properties, message.getBytes());
        channel.close();
        connection.close();
    }
}
