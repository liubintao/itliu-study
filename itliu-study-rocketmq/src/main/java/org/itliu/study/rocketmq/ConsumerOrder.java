package org.itliu.study.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @desc
 * @auther itliu
 * @date 2020/9/2
 */
public class ConsumerOrder {

    private static final String topic = "testtopic001";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("xxooo");
        consumer.setNamesrvAddr("10.0.0.5:9876");

        consumer.subscribe(topic, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                for (MessageExt ext : list) {
                    System.out.println(new String(ext.getBody()) + "_" + ext.getQueueId() + "_" + Thread.currentThread().getName());
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //必须用一个线程去消费才能保证顺序消费
        consumer.setConsumeThreadMin(1);
        consumer.setConsumeThreadMax(1);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.start();

        System.out.println("Consumer 01 start...");
    }
}
