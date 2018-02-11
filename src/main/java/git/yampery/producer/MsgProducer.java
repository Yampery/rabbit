package git.yampery.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @decription MsgProducer
 * <p>生产者</p>
 * @author Yampery
 * @date 2018/2/11 11:44
 */
@Component
public class MsgProducer {

    @Resource
    private AmqpTemplate amqpTemplate;
    @Value("${sms.delay.exchange}") private String SMS_DELAY_EXCHANGE;
    @Value("${sms.exchange}") private String SMS_EXCHANGE;
    @Value("${sms.route.key}") private String SMS_ROUTE_KEY;

    /**
     * 延迟消息放入延迟队列中
     * @param msg
     * @param expiration
     */
    public void publish(String msg, String expiration) {
        amqpTemplate.convertAndSend(SMS_DELAY_EXCHANGE, SMS_ROUTE_KEY, msg, message -> {
            // 设置消息属性-过期时间
            message.getMessageProperties().setExpiration(expiration);
            return message;
        });
    }

    /**
     * 非延迟消息放入待消费队列
     * @param msg
     */
    public void publish(String msg) {
        amqpTemplate.convertAndSend(SMS_EXCHANGE, SMS_ROUTE_KEY, msg);
    }
}
