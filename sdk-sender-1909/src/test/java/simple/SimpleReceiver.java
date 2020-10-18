package simple;

import com.rabbitmq.client.*;

/**
 * @author 徒有琴
 */
public class SimpleReceiver {
    private final static String QUEUE_NAME="test_queue";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(10);
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
        channel.basicConsume(QUEUE_NAME,false,consumer);
//        channel.close();
//        connection.close();
    }
}
