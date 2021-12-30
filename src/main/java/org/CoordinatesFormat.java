package org;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.JsonParseDemo.getFileEncode;

/**
 * @author zhanggh
 * @description: 坐标格式转换
 * @date 2021/7/12 10:11
 */

public class CoordinatesFormat {

    /**
     * 读取的文件地址
     */
    private static final String READ_FILEPATH = "qywl.md";

    public static void main(String[] args) {
//        String s = readFileContent(READ_FILEPATH);
        String s = "1/2/3/123/123/1/32/31/23/123/5/5/6//e/das/d/ds//re/ge/d/ds";
//
//        String sss = s.replace(",", " ")
//                .replace("] [", ",")
//                .replace("[[[", "MULTIPOLYGON(((")
//                .replace("]]]", ")))")
//                .replace("[","").replace("]","")
//                ;
//        System.out.println(simpleReplace(s));
//        System.out.println(replace(s));
//        System.out.println(simpleReplace(s).equals(replace(s)));
        String[] s1 = s.split("/");
        List<String> strings = Arrays.asList(s1);
        System.out.println(strings);


    }

    /**
     * 优化后的格式转换
     * @param coordinates
     * @return
     */
    public static String simpleReplace(String coordinates){
        return coordinates.replace(",", " ")
                .replace("] [", ",")
                .replace("[[[", "MULTIPOLYGON(((")
                .replace("]]]", ")))")
                .replace("[", "")
                .replace("]", "")
                ;
    }

    /**
     *
     * 格式转换
     * @param coordinates
     * @return
     */
    public static String replace(String coordinates){
        return coordinates.replace('[', '(')
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
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt + "\n");
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

}
