package org;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @description: 上传文件到hadoop中
 * @author zhanggh
 * @date 2021/9/14 17:12
 */

public class InputFile {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // TODO Auto-generated method stub
//        文件路径
        String source="C:\\Users\\zgh98\\Desktop\\aliyun acount.md";
//        上传路径
        String dest ="hdfs://192.168.220.128:9000/usr/zgh";
        putfile(source,dest);
    }

    public static void putfile(String source,String dest) throws IOException,URISyntaxException {
        //读取 hadoop 文件系统的配置
        Configuration conf =new Configuration();
        URI uri =new URI("hdfs://192.168.220.128:9000");
        // FileSystem是用户操作HDFS的核心类，它获得URI对应的HDFS文件系统
        FileSystem fileSystem=FileSystem.get(uri,conf);
        //源文件路径
        Path srcPath=new Path(source);
        //目的路径
        Path dstPath=new Path(dest);
        //查看目的路径是否存在
        if ( !(fileSystem.exists(dstPath))) {
            fileSystem.mkdirs(dstPath);
        }
        //得到本地文件名称
        String fileName=source.substring(source.lastIndexOf('/')+1,source.length());
        try {
            //将本地文件上传到 HDFS
            fileSystem.copyFromLocalFile(srcPath, dstPath);
            System.out.println("File " + fileName + " copied to " + dest);
            System.out.println("上传成功");
        }catch(Exception e) {
            System.err.println("Exception caught! :" + e);
            System.exit(1);
        }finally {
            fileSystem.close();
        }
    }

}