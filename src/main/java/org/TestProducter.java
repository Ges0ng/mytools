package org;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

/**
 * @author zhanggh
 * @description: TODO
 * @date 2021/10/18 9:48
 */

public class TestProducter {

    public static void main(String[] args) throws  Exception{
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "192.168.220.128:9092");
        // 等待所有副本节点的应答 "-1"与"all"相同
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
//        for (int i = 0; i < 50; i++) {
//            System.out.println(i);
////       producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), "hello world->" + i));
//            producer.send(new ProducerRecord<String, String>("wuhu", Integer.toString(i), "hello world->" + i));
////            producer.send(new ProducerRecord<String, String>("wuhu", "test", "摩西摩西"));
//        }
        Scanner topic = new Scanner(System.in);
        System.out.println("请选择你要发送的topic：");
        String top = null;
        if (topic.hasNext()) {
            top = topic.next();
        }

        Scanner keys = new Scanner(System.in);
        System.out.println("请选择你要发送的key：");
        String key = null;
        if (keys.hasNext()) {
            key = keys.next();
        }

        Scanner value = new Scanner(System.in);
        System.out.println("请选择你要发送的消息：");
        String val = null;
        if (value.hasNext()) {
            val = value.next();
        }

        producer.send(new ProducerRecord<String, String>(top, "bzzb", val));
        System.out.println("producer close...");
        value.close();
        producer.close();
    }
}
