package org.example;

import com.aliyun.odps.udf.UDF;

public class test extends UDF {

    public static void main(String[] args) {
        System.out.println(evaluate("as"));
    }

    // TODO define parameters and return type, e.g:  public String evaluate(String a, String b)
    public static String evaluate(String s) {
        return "hello world:" + s;
    }
}