package org.example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.odps.udf.UDF;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.util.Date;

/**
 * @author zhanggh
 * @description: geojson解析
 * @date 2021/6/7 17:15
 */
public class JsonParseDemo extends UDF {

    private static final String fileName = "中区";

    /**
     * 读取的文件地址
     */
    private static final String READ_FILEPATH = "C:\\Users\\zgh98\\Desktop\\cq数据\\7.16中区\\" + fileName + ".geojson";

    /**
     * 输出的文件地址
     */
    private static final String WRITE_FILEPATH = "C:\\Users\\zgh98\\Desktop\\cq数据\\7.16中区\\中区.txt";

    /**
     * 用ThreadLocal记录过程时间
     */
    static ThreadLocal<Long>  startTime = new ThreadLocal<>();



    public static void main(String[] args) {

        //解析json
        String content = jsonParse_name(READ_FILEPATH);
        System.out.println(content);
//        //输出到文件
//        write(WRITE_FILEPATH, content);



//        gaodeApi(READ_FILEPATH,WRITE_FILEPATH);
    }


    public static void gaodeApi(String read_path,String write_path){
        String content = null;
        try {
            //开始时间
            startTime.set(System.currentTimeMillis());
            content = jsonParse_gaodeApi_name(read_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //写入
        write(write_path, content);
        System.out.println(content);

        System.out.println("耗时 : " + millisToStringShort(System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }

    /**
     * 解析json文件为String，name格式
     * @param filePath
     * @return
     *
     * fastjson
     */
    public static String jsonParse_name(String filePath){
        //读取文件内容
        String data = readFileContent(filePath);
        // 解析内容
        JSONObject jsonObject = JSONObject.parseObject(data);
        //拿到features数组
        JSONArray features = jsonObject.getJSONArray("features");

        StringBuilder sb = new StringBuilder();
        //拼接第一行的字段
        sb.append("name"+"\t"+"code"+"\t"+"type"+"\t"+"coordinates"+"\n");
//        .append("month"+"\t"+"adcode"+"\n");
        //遍历数组数据
        for (int i = 0; i < features.size(); i++) {
            //获取最外层features
            JSONObject singleFeatures = features.getJSONObject(i);
            //获取properties中的Name和code
            Object name = singleFeatures.getJSONObject("properties").get("Name");         //不同情况下
            Object code = singleFeatures.getJSONObject("properties").get("code");
//            Object name = singleFeatures.getJSONObject("properties").get("F");
//            Object code = singleFeatures.getJSONObject("properties").get("ID");
            //type固定内容
            Object type = "0102";
            Object month = "202106";
            Object adcode = "500000";
            //遍历geometry获取coordinates
            Object geometry = singleFeatures.getJSONObject("geometry").get("coordinates");
            //拼接需要字段的字符串
            sb.append(name).append("\t")
                    .append(code).append("\t")
                    .append(type).append("\t")
//                    .append(geometry).append("\t");
                    .append(geometry).append("\n");
//                    .append(month).append("\t")
//                    .append(adcode).append("\n");
        }
        System.out.println(new Date() +"：json解析完成");
        return sb.toString();
    }

    /**
     * 解析json文件为String，F格式
     * @param filePath
     * @return
     *
     * fastjson
     */
    public static String jsonParse(String filePath){
        //读取文件内容
        String data = readFileContent(filePath);
        // 解析内容
        JSONObject jsonObject = JSONObject.parseObject(data);
        //拿到features数组
        JSONArray features = jsonObject.getJSONArray("features");

        StringBuilder sb = new StringBuilder();
        //拼接第一行的字段
        sb.append("name"+"\t"+"code"+"\t"+"type"+"\t"+"coordinates"+"\n");
//        .append("month"+"\t"+"adcode"+"\n");
        //遍历数组数据
        for (int i = 0; i < features.size(); i++) {
            //获取最外层features
            JSONObject singleFeatures = features.getJSONObject(i);
            //获取properties中的Name和code
//            Object name = jsonObject.get("name");         //不同情况下
            Object name = singleFeatures.getJSONObject("properties").get("F");
            Object code = singleFeatures.getJSONObject("properties").get("ID");
            //type固定内容
            Object type = "0103";
            Object month = "202106";
            Object adcode = "500000";
            //遍历geometry获取coordinates
            Object geometry = singleFeatures.getJSONObject("geometry").get("coordinates");
            //拼接需要字段的字符串
            sb.append(name).append("\t")
                    .append(code).append("\t")
                    .append(type).append("\t")
//                    .append(geometry).append("\t");
                    .append(geometry).append("\n");
//                    .append(month).append("\t")
//                    .append(adcode).append("\n");
        }
        System.out.println(new Date() +"：json解析完成");
        return sb.toString();
    }


    /**
     * 解析json文件为String,接入高德api，高德格式
     * @param filePath
     * @return
     *
     * fastjson
     */
    public static String jsonParse_gaodeApi_name(String filePath) throws Exception {

        String evaluate = "";

        //读取文件内容
        String data = readFileContent(filePath);
        // 解析内容
        JSONObject jsonObject = JSONObject.parseObject(data);
        //拿到features数组
        JSONArray features = jsonObject.getJSONArray("features");

        StringBuilder sb = new StringBuilder();
        //拼接第一行的字段
        sb.append("name"+"\t"+"code"+"\t"+"type"+"\t"+"coordinates"+"\n");
//        .append("month"+"\t"+"adcode"+"\n");
        //遍历数组数据
        for (int i = 0; i < features.size(); i++) {
//        for (int i = 0; i < 100; i++) {
            //获取最外层features
            JSONObject singleFeatures = features.getJSONObject(i);
            //获取properties中的Name和code
//            Object name = jsonObject.get("name");         //不同情况下
            Object name = singleFeatures.getJSONObject("properties").get("F");
            Object code = singleFeatures.getJSONObject("properties").get("ID");
            //type固定内容
            Object type = "0101";
            Object month = "202106";
            Object adcode = "500000";
            //遍历geometry获取coordinates
            Object coordinates = singleFeatures.getJSONObject("geometry").get("coordinates");

            //转换标准格式
            Object format = formatCoordinates(coordinates);
            //转换中心点
            evaluate = AreaCoorsCenter.evaluate(format.toString());
            //高德api接口获取
            String gaodeJson = ApiDoGet.doGet(evaluate);
            //解析高德的json串
            JSONObject  gaodeJson_parse = JSONObject.parseObject(gaodeJson);
            Object formatted_address = gaodeJson_parse.getJSONObject("regeocode").get("formatted_address");
            Object poiName = null;
            try{
                //如果没有poi站点会报错，取默认address
                poiName = gaodeJson_parse.getJSONObject("regeocode").getJSONArray("pois").getJSONObject(0).get("name");
            }catch (Exception e){
                poiName = formatted_address;
            }
//            if (poiName.toString().contains("(建设中)")) {
//                poiName = gaodeJson_parse.getJSONObject("regeocode").getJSONArray("pois").getJSONObject(1).get("name");
//            }
            try{
                if (poiName.toString().contains("建设中") || poiName.toString().contains("暂停营业")) {
                    poiName = gaodeJson_parse.getJSONObject("regeocode").getJSONArray("pois").getJSONObject(1).get("name");
                }
            }catch (Exception e){
                poiName = formatted_address;
            }

                //拼接需要字段的字符串
                sb.append(poiName).append("\t")
                        .append(code).append("\t")
                        .append(type).append("\t")
                        .append(coordinates).append("\n");

            System.out.println(new Date() + ":" + evaluate + "==" + poiName.toString() + "---" + i);
        }
        System.out.println(new Date() +"：json解析完成");
        return sb.toString();
    }


    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static String readFileContent(String filePath)  {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        //判断文件是否存在
        if (file.isFile() && file.exists()) {
            InputStreamReader read = null;
            try {
                String encoding = getFileEncode(file);
//                String encoding = "ISO-8859-1";
//                String encoding = "ASCII";
                read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                }
                System.out.println(new Date() + "：文件读取完成,编码格式为" + encoding);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    assert read != null;
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new NullPointerException("文件不存在");
        }
        return sb.toString();
    }

    /**
     * 打印内容到文件
     *
     * @param content
     */
    private static void write(String filePath, String content) {
        FileWriter fileWriter = null;
        try {
            boolean fugai = false;
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fileWriter = new FileWriter(filePath, fugai);
            //打印内容
            fileWriter.write(content);
            System.out.println(new Date() + "：文件输出成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void write(String filePath, String content,boolean flag) {
        FileWriter fileWriter = null;
        try {

            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fileWriter = new FileWriter(filePath, flag);
            //打印内容
            fileWriter.write(content);
            System.out.println(new Date() + "：文件输出成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断文件编码格式
     *
     * @param file
     * @return
     * @throws IOException
     *
     *  juniversalchardet
     *  commons-lang3
     */
    public static String getFileEncode(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        String code = "utf-8";
        byte[] buf = new byte[4096];
        UniversalDetector detector = new UniversalDetector(null);

        // (2)
        int nread;
        while ((nread = in.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        // (3)
        detector.dataEnd();

        // (4)
        String encoding = detector.getDetectedCharset();
        if (StringUtils.isNotEmpty(encoding)) {
            code = encoding;
        }
        return code;
    }


    /**
     * 换成标准格式
     * @param coordinates
     * @return
     */
    public static Object  formatCoordinates(Object coordinates) {
        Object replace =
                coordinates.toString().replace('[', '(')
                        .replace(']', ')')
                        .replace("))),(((", "));((")
                        .replace("),(", ");(")
                        .replace(",", " ")
                        .replace(";", ",")
                        .replace("(((", "MULTIPOLYGON(((")
                        .replace("((((", "MULTIPOLYGON(((")
                        .replace("MULTIPOLYGONMULTIPOLYGON(((", "MULTIPOLYGON(((")
                        .replace("))))", ")))")
                        .replace("),(", ",")
                        .replace("),(", ",");
        return replace;
    }

    /**
     * 把毫秒数转换成时分秒
     * @param millis
     * @return
     */
    public static String millisToStringShort(long millis) {
        StrBuilder strBuilder = new StrBuilder();
        long temp = millis;
        long hper = 60 * 60 * 1000;
        long mper = 60 * 1000;
        long sper = 1000;
        if (temp / hper > 0) {
            strBuilder.append(temp / hper).append("小时");
        }
        temp = temp % hper;

        if (temp / mper > 0) {
            strBuilder.append(temp / mper).append("分钟");
        }
        temp = temp % mper;
        if (temp / sper > 0) {
            strBuilder.append(temp / sper).append("秒");
        }
        return strBuilder.toString();
    }


}

