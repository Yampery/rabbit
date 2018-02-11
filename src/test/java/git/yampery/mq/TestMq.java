package git.yampery.mq;

import com.alibaba.fastjson.JSONObject;
import git.yampery.producer.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @decription TestMq
 * <p>测试</p>
 * @author Yampery
 * @date 2018/2/11 15:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMq {

    @Resource
    private MsgProducer producer;

    @Test
    public void testMq() {
        JSONObject jObj = new JSONObject();
        jObj.put("msg", "这是一条短信");
        producer.publish(jObj.toJSONString(), String.valueOf(10 * 1000));
    }
}
