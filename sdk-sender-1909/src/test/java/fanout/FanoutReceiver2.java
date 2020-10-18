package fanout;

import com.rabbitmq.client.*;
import simple.ConnectionUtil;

/**
 * @author 徒有琴
 */
public class FanoutReceiver2 {
    private static String exchange_name = "exchanhe_name_fanout";
    private static String queue_name = "fanout_queue1";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue_name, false, false, false, null);
        //加交换机
        channel.exchangeDeclare(exchange_name, BuiltinExchangeType.FANOUT);
        channel.queueBind(queue_name,exchange_name,"");

        System.out.println("消费者2");
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
            {
                try {
                    String msg=new String(body,"utf-8");
                    System.out.println(msg);
                    channel.basicAck(envelope.getDeliveryTag(),false);//告诉服务器我收到消息了
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(queue_name,false,consumer);
    }
}
