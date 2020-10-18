package topic;

import com.rabbitmq.client.*;
import simple.ConnectionUtil;

/**
 * @author 徒有琴
 */
public class TopicReceiver {
    private static String exchange_name = "exchanhe_name_topic";
    private static String queue_name = "topic_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue_name, false, false, false, null);
        //加交换机
        channel.exchangeDeclare(exchange_name, BuiltinExchangeType.TOPIC);
        channel.queueBind(queue_name,exchange_name,"key.*");//模糊匹配 *是通配符

        System.out.println("消费者1");
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
            {
                try {
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
