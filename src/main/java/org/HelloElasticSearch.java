package org;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author zhanggh
 * @description: TODO
 * @date 2021/10/21 10:21
 */

public class HelloElasticSearch {

//    public static void main(String[] args) throws IOException {
//        test();
//    }

    public static void main(String[] args) throws IOException {
        try {
            // 设置集群名称biehl01,Settings设置es的集群名称,使用的设计模式，链式设计模式、build设计模式。
            Settings settings = Settings.builder().put("cluster.name", "my-application").build();
            // 读取es集群中的数据,创建client。
            @SuppressWarnings("resource")
            TransportClient client = new PreBuiltTransportClient(settings).addTransportAddresses(
                    // 用java访问ES用的端口是9300。es的9200是restful的请求端口号
                    new InetSocketTransportAddress(InetAddress.getByName("192.168.220.128"), 9300));
            // 搜索数据(.actionGet()方法是同步的，没有返回就等待)
            // 方式是先去索引里面查询出索引数据,再去文档里面查询出数据。
            GetResponse response = client.prepareGet("lib5", "fulltext", "2").execute().actionGet();
            // 输出结果
            System.out.println(response);
            // 关闭client
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        test3();

    }

    //插入数据
    public static void test() throws IOException {
        //指定ES集群
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();
        //创建访问ES服务器的客户端
        @SuppressWarnings("resource")
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddresses(
                // 用java访问ES用的端口是9300。es的9200是restful的请求端口号
                new InetSocketTransportAddress(InetAddress.getByName("192.168.220.128"), 9300));

        XContentBuilder doc = XContentFactory.jsonBuilder()
                .startObject()
                .field("id","1")
                .field("title","我在学习es插入操作")
                .field("content","好好学习，天天向上")
                .endObject();

        //添加一个doc
        IndexResponse response = client.prepareIndex("lib5","testadd",null)//id为null，由ES自己生成
                .setSource(doc).get();
        System.out.println(response.status());//打印添加是否成功
        client.close();
    }

    //删除文档
    public static void test3() throws IOException {
        //指定ES集群
        Settings settings = Settings.builder().put("cluster.name",
                "my-application").build();

        //创建访问ES服务器的客户端
        @SuppressWarnings("resource")
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddresses(
                // 用java访问ES用的端口是9300。es的9200是restful的请求端口号
                new InetSocketTransportAddress(InetAddress.getByName("192.168.220.128"), 9300));

        DeleteResponse response = client.prepareDelete("lib5","testadd","2")
                .get();
        System.out.println(response.status());//打印添加是否成功
        client.close();
    }
}
