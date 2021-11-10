package org;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用高德api获取json 传参 经度，纬度
 * @author zhanggh
 * @description: TODO
 * @date 2021/6/21 21:12
 */
public class ApiDoGet {

    public static String doGet(String jwd) throws Exception {
        /*
          请求地址: * 例如:https://jisuapilsplateluck.api.bdymkt.com/lsplateluck/query
          String host = "https://jisuapilsplateluck.api.bdymkt.com"; * String path = "/lsplateluck/query"; * method:请求方式 GET
          appcode:秘钥
         */
        String host = "https://restapi.amap.com";
        String path = "/v3/geocode/regeo";
        String method = "GET";
//        String jwd = "106.3780320281698,29.36978256729465";
        String appcode = "fcfb8b06d51d9dd65d472a7d7aa22a13";
        Map<String, String> headers = new HashMap<>();
//        headers.put("X-Bce-Signature", "AppCode/" + appcode);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        //这里添加请求参数
        Map<String, String> querys = new HashMap<>();
        querys.put("key", appcode);
        querys.put("location",jwd);
        //有限定条件
        querys.put("poitype","120201|120202|060100|060101|060102|170300|090100|090101|090102|120100|130100|130101|130102|130103|130104|130105|130106|130107|141200|141201|141202||141203|141204|141205|141206|120300|120301|120302|120303|120304");
        querys.put("radius","1000");
        querys.put("extensions","all");
        querys.put("batch","false");
        querys.put("roadlevel","0");

            /*
              重要提示如下: * HttpUtils 请从

             https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java * 下载

              相应的依赖请参照
              https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取 response 的 body
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity, "utf-8");
        System.out.println(EntityUtils.toString(entity, "utf-8"));

            return s;

    }

    public static void main(String[] args) {
        String jwd = "106.3780320281698,29.36978256729465";
//        String jwd = "106.51979084726572,29.350619831075342";
        String s = null;
        try {
            s = doGet(jwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
