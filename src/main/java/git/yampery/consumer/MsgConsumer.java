package git.yampery.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @decription MsgConsumer
 * <p>消费者</p>
 * @author Yampery
 * @date 2018/2/11 11:43
 */
public class MsgConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String msg;
        try {
            // 线程每秒消费一次
            Thread.sleep(1000);
            msg = new String(message.getBody(), "utf-8");
            System.out.println(msg);

        } catch (Exception e) {
            // 这里并没有对服务异常等失败的消息做处理，直接丢弃了
            // 可以选择路由到另外一个专门处理消费失败的队列
            return;
        }
    }
}
