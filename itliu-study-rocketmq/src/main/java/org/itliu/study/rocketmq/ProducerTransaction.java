package org.itliu.study.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @desc
 * @auther itliu
 * @date 2020/9/2
 */
public class ProducerTransaction {

    private static final String topic = "testtopic001";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {
        TransactionMQProducer producer = new TransactionMQProducer();
        producer.setProducerGroup("pgroup");
        producer.setNamesrvAddr("10.0.0.5:9876");
        final int[] i = {0};
        producer.setTransactionListener(new TransactionListener() {
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                System.out.println("execute ...");
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("check ..." + ++i[0]);
                return LocalTransactionState.UNKNOW;
            }
        });
        producer.start();
        while (true) {
            InputStream in = System.in;
            Scanner scanner = new Scanner(in);
            System.out.println("输入:");
            String next = scanner.next();
            if (next == "end") {
                break;
            }

            Message msg = new Message(topic, next.getBytes());
            producer.sendMessageInTransaction(msg, null);
            System.out.println("==============================");
        }
        System.in.read();
        producer.shutdown();
    }
}
