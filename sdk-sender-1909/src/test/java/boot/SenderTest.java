package boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 徒有琴
 */
//表示启用spring容器
@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {
    @Autowired
    private Sender sender;

    @Test
    public void test1() throws InterruptedException {
        sender.send("测试消息");
    }
}
