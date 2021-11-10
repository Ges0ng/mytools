package org;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author zhanggh
 * @description: TODO
 * @date 2021/9/14 17:15
 */

public class ReadFile {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // TODO Auto-generated method stub
        Configuration conf=new Configuration();
//        指定hadoop的uri
        URI uri =new URI("hdfs://192.168.220.128:9000");
        FileSystem fs=FileSystem.get(uri,conf);
        //读取数据
        Path filePath=new Path("hdfs://192.168.220.128:9000/usr/zgh/aliyun acount.md");

        FSDataInputStream in=fs.open(filePath);
        IOUtils.copyBytes(in, System.out, conf);
        in.close();
    }

}