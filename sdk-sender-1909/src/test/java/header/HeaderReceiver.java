package header;

import com.rabbitmq.client.*;
import simple.ConnectionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徒有琴
 */
public class HeaderReceiver {
    private static String exchange_name = "exchanhe_name_header";
    private static String queue_name = "head_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue_name, false, false, false, null);
        //加交换机
        channel.exchangeDeclare(exchange_name, BuiltinExchangeType.HEADERS);
        Map<String, Object> header = new HashMap<>();
        //header.put("haoge", "666");
        header.put("format", "pdf");
        channel.queueBind(queue_name,exchange_name,"",header);

        System.out.println("消费者1");
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
            {
                try {
                    System.out.println(properties);
                    String msg=new String(body,"utf-8");
                    System.out.println(msg);//给浩哥发钱，操作数据库去发钱
                    //当业务处理完之后，才通知服务器
                    channel.basicAck(envelope.getDeliveryTag(),false);//告诉服务器我收到消息了
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(queue_name,false,consumer);
    }
}
