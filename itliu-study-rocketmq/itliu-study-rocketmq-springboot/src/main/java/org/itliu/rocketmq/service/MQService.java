package org.itliu.rocketmq.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc
 * @auther itliu
 * @date 2020/9/7
 */
@Service
public class MQService {

    @Autowired
    private DefaultMQProducer producer;

    public Object sendMsg(String string) {

        for (int i = 0; i < 1; i++) {
            Message message = new Message("tpk02", "xx".getBytes());

            try {
                return producer.send(message);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
