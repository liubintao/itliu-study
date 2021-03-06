package org.itliu.study.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @desc
 * @auther itliu
 * @date 2020/9/2
 */
public class ProducerBatch {

    private static final String topic = "testtopic001";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup("pgroup");
        producer.setNamesrvAddr("10.0.0.5:9876");
        producer.start();
        List<Message> messageList = new ArrayList<Message>();
        while (true) {
            InputStream in = System.in;
            Scanner scanner = new Scanner(in);
            System.out.println("输入:");
            String next = scanner.next();
            if (next.equals("end")) {
                break;
            }
            Message msg = new Message(topic, next.getBytes());
            messageList.add(msg);
        }
        producer.send(messageList);
        System.out.println("==============================");
        System.in.read();

    }
}
