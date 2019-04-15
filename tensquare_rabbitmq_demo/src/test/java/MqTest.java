import com.tensquare.rabbitmq.RabbitmpApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RabbitmpApplication.class)
public class MqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRabbitmq() {
        rabbitTemplate.convertAndSend("itcast", "我要红包");
    }

    @Test
    public void testSendFanout() {
        rabbitTemplate.convertAndSend("chuanzhi", "", "分列模式走起 Fanout");
    }

    @Test
    public void testTopic1() {
        rabbitTemplate.convertAndSend("topictest", "goods.aa", "主题模式 topic");
    }
    @Test
    public void testTopic2() {
        rabbitTemplate.convertAndSend("topictest", "rabbitmq.goods.log", "主题模式 topic");
    }
    @Test
    public void testTopic3() {
        rabbitTemplate.convertAndSend("topictest", "goods.log", "主题模式 topic");
    }
}