package odps.utils;

import com.aliyun.odps.udf.UDF;



/**
 * @author zhanggh
 */
public class Xqwl extends UDF {

    //lon  n* 0.001174   lat n* 0.0009


    /**
     * radius以百米为单位， 1=100米， 0.5=50米
     * @param lng
     * @param lat
     * @param radius
     * @return
     */
    public String evaluate(Double lng, Double lat, String radius){
        Double r = Double.parseDouble(radius);
        //左右移动
        Double firstPointLng = lng + (r * 0.001048);
        Double secondPointLng = lng - (r * 0.001048);
//        Double firstPointLng = lng;
//        Double secondPointLng = lng;
        //上下移动
        Double firstPointLat = lat + (r * 0.0009);
        Double secondPointLat = lat - (r * 0.0009);
//        Double firstPointLat = lat;
//        Double secondPointLat = lat;

        //拼接
        String square =
                "[" +
                        "[" + firstPointLng + "," + firstPointLat + "],"
                        + "[" + firstPointLng + "," + secondPointLat + "],"

                        + "[" + secondPointLng + "," + secondPointLat + "],"

                        + "[" + secondPointLng + "," + firstPointLat + "],"
                        + "[" + firstPointLng + "," + firstPointLat + "]"
                        + "]"
                ;
//        String square =
//                         firstPointLng + "," + firstPointLat + ","
//                        + firstPointLng + "," + secondPointLat + ","
//                        + secondPointLng + "," + secondPointLat + ","
//                        + secondPointLng + "," + firstPointLat + ","
//                        + firstPointLng + "," + firstPointLat + ""
//                        ;

        return square;
    }

//    private static final Double PI = Math.PI;
//
//    private static final Double PK = 180 / PI;
//    private static double EARTH_RADIUS = 6378.137;
//
//    private static double rad(double d) {
//        return d * Math.PI / 180.0;
//    }
//    private static final double EARTH_RADIUS = 6371393; // 平均半径,单位：m；不是赤道半径。赤道为6378左右
//    public static double getDistance(Double lat1,Double lng1,Double lat2,Double lng2) {
//        // 经纬度（角度）转弧度。弧度用做参数，以调用Math.cos和Math.sin
//        double radiansAX = Math.toRadians(lng1); // A经弧度
//        double radiansAY = Math.toRadians(lat1); // A纬弧度
//        double radiansBX = Math.toRadians(lng2); // B经弧度
//        double radiansBY = Math.toRadians(lat2); // B纬弧度
//
//        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，获得∠AOB的cos值
//        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
//                + Math.sin(radiansAY) * Math.sin(radiansBY);
//
//        System.out.println(cos);
////        System.out.println("cos = " + cos); // 值域[-1,1]
//        double acos = Math.acos(cos); // 反余弦值
//        System.out.println(acos);
////        System.out.println("acos = " + acos); // 值域[0,π]
////        System.out.println("∠AOB = " + Math.toDegrees(acos)); // 球心角 值域[0,180]
//        return EARTH_RADIUS * acos; // 最终结果
//    }

//    public static double getDistance(double lat1, double lng1, double lat2,
//                                     double lng2) {
//        double radLat1 = rad(lat1);
//        double radLat2 = rad(lat2);
//        double a = radLat1 - radLat2;
//        double b = rad(lng1) - rad(lng2);
//        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
//                + Math.cos(radLat1) * Math.cos(radLat2)
//                * Math.pow(Math.sin(b / 2), 2)));
//        s = s * EARTH_RADIUS;
//        s = Math.round(s * 10000d) / 10000d;
//        s = s * 1000;
//        return s;
//    }


    public static void main(String[] args) {
        Xqwl xqwl = new Xqwl();
        System.out.println(xqwl.evaluate( 120.947644,30.977048
                , "0.5"));
    }
}
