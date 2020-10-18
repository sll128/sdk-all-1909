package boot;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 徒有琴
 */
@SpringBootApplication
public class MQStarter {
    public static void main(String[] args) {
        SpringApplication.run(MQStarter.class, args);
    }

    //队列
    @Bean
    public Queue test1() {//方法名是bean的id
        Queue queue = new Queue("test1");
        return queue;
    }

    //工厂
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("10.9.45.155");
        factory.setPort(5672);
        factory.setVirtualHost("/test");
        factory.setUsername("luke");
        factory.setPassword("luke");
        factory.setPublisherReturns(true);
        factory.setPublisherConfirms(true);
        return factory;
    }
}
