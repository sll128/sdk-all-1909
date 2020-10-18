package simple;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author 徒有琴
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.9.45.155");
        factory.setPort(5672);
        factory.setVirtualHost("/test");
        factory.setUsername("luke");
        factory.setPassword("luke");
        Connection connection = factory.newConnection();
        return connection;
    }
}
