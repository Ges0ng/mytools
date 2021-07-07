package org.example;

import com.aliyun.odps.udf.UDF;

public class demo extends UDF {
    // TODO define parameters and return type, e.g:  public String evaluate(String a, String b)
    public String evaluate(String s) {
        return "hello world:" + s;
    }
}