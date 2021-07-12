package org.example;

/**
 * @author zhanggh
 * @description: 坐标格式转换
 * @date 2021/7/12 10:11
 */

public class CoordinatesFormat {

    /**
     * 格式转换
     * @param jwdc
     * @return
     */
    public static String replace(String jwdc){
        return jwdc.replace('[', '(')
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
}
