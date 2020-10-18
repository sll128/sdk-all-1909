package direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import simple.ConnectionUtil;

/**
 * @author 徒有琴
 */
public class DirectSender {
    private static String exchange_name = "exchanhe_name_direct";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchange_name, BuiltinExchangeType.DIRECT);
        String message = "hello abc key";
        channel.basicPublish(exchange_name, "key", null, message.getBytes());
        channel.close();
        connection.close();
    }
}
