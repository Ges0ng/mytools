package odps.utils;

import com.aliyun.odps.udf.UDF;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;


/**
 * @author zhanggh
 * @Date 2022/4/15 17:09
 * TODO: 判断点是否在经纬度线段上
 * 求距离误差在分辨率的范围内就认为点在线上
 * 判断地图上的一个点是否在已知的一条线上；首先地球为球面，经纬度为double类型保留有6~14位小数，直接的求距离不太合适。
 */
public class PointOnLine extends UDF {

    // 地球半径
    private static final double EARTH_RADIUS = 6378137;
    private static GeometryFactory gf = new GeometryFactory();

    /**
     * wkt 转geometry
     *
     * @param wkt
     * @return
     * @throws ParseException
     */
    public static Geometry wkt2Geo(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(gf);
        Geometry geom = reader.read(wkt);
        return geom;
    }

    /**
     * 计算两个点之间的距离
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double distance(Point p1, Point p2) {
        return distance(p1.getCoordinate(), p2.getCoordinate());
    }

    private static double haverSin(double theta) {
        double v = Math.sin(theta / 2);
        return v * v;
    }

    /**
     * 计算球面两点距离
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double distance(Coordinate p1, Coordinate p2) {
        // 用haversine公式计算球面两点间的距离。
        // 经纬度转换成弧度
        double lat1 = Math.toRadians(p1.y);
        double lon1 = Math.toRadians(p1.x);
        double lat2 = Math.toRadians(p2.y);
        double lon2 = Math.toRadians(p2.x);
        // 差值
        double vLon = Math.abs(lon1 - lon2);
        double vLat = Math.abs(lat1 - lat2);
        // h is the great circle distance in radians, great
        // circle就是一个球体上的切面，它的圆心即是球心的一个周长最大的圆。
        double h = haverSin(vLat) + Math.cos(lat1) * Math.cos(lat2) * haverSin(vLon);
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));
        return distance;
    }

    /**
     * 判断点是否在线上
     *
     * @param a          点A
     * @param start      线起点start
     * @param end        线终点end
     * @param resolution 误差范围m
     * @return
     */
    public static boolean isPointOnSegment(Point a, Point start, Point end, double resolution) {
        boolean flag = false;
        double startAdis = distance(a, start);
        System.out.println(startAdis);
        double endADis = distance(a, end);
        System.out.println(endADis);
        double dis = distance(start, end);
        System.out.println(dis);
        if (startAdis + endADis >= dis - resolution && startAdis + endADis <= dis + resolution) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断点是否在线上
     * @param lineCoordinate 线的坐标经纬度串
     * @param pointCoordinate 点的坐标经纬度串
     * @param resolution 精度误差 范围m
     * @return
     */
    public Integer evaluate(String lineCoordinate,String pointCoordinate,double resolution){
        Integer flag = -1;
        try {
            //经纬度串线路和点坐标
            String lineString = "LINESTRING (" + lineCoordinate.replace(",", " ").replace(";", ",") + ")";
            String multiPoints = "MULTIPOINT ((" + pointCoordinate.replace(",", " ") + "))";
            //转换格式
            Geometry line = wkt2Geo(lineString);
            Geometry multiPoint = wkt2Geo(multiPoints);
            for (Coordinate coordinate : multiPoint.getCoordinates()) {
                // 判断点是否在线上
                Point point = gf.createPoint(coordinate);
                // 判断点是否在线上  geom.intersects(point)、point.within(geom) 这俩方法都不管用，以1/20分辨率当作误差范围
                if (isPointOnSegment(point, gf.createPoint(line.getCoordinates()[0]), gf.createPoint(line.getCoordinates()[1]), resolution)) {
                    System.out.println(point.getCoordinate() + " 在线上");
                    flag=1;
                } else {
                    System.err.println(point.getCoordinate() + " 不在线上");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        PointOnLine pointOnLine = new PointOnLine();
        //线几何
        String lineCoordinate = "120.754878,30.774927;120.754323,30.774886;120.754318,30.774886;120.754172,30.774875;120.753889,30.774852";
        //点几何
        String pointCoordinate = "120.754336 30.774822";
        pointOnLine.evaluate(lineCoordinate,pointCoordinate,10);
    }
//    public static void main(String[] args) throws ParseException, IOException {
//        // 线几何
////        String lineString = "LINESTRING (117.18292236328126 40.16208338164619, 119.01489257812501 39.48284540453334)";
//        String lineString = "LINESTRING (120.754878,30.774927;120.754323,30.774886;120.754318,30.774886;120.754172,30.774875;120.753889,30.774852)";
//        lineString = lineString.replace(",", " ").replace(";", ",");
//        // 多点几何
////        String multPoints = "MULTIPOINT ((118.23143005371095 39.64059509088577),(118.23143005371095 39.64006632964757),(118.23143005371095 39.639537564366705),(118.23211669921876 39.77476948529546),(118.23211669921876 39.77424175134451))";
//        String multPoints = "MULTIPOINT ((120.754336 30.774822))";
//        Geometry line = wkt2Geo(lineString);
//        System.out.println(line);
//        Geometry mulpoint = wkt2Geo(multPoints);
//        System.out.println(mulpoint);
////        double resolution = 305.7481;
////        误差值m
//        double resolution = 10.7481;
//        for (Coordinate coordinate : mulpoint.getCoordinates()) {
//            // 判断点是否在线上
//            Point point = gf.createPoint(coordinate);
//            // 判断点是否在线上  geom.intersects(point)、point.within(geom) 这俩方法都不管用，以1/20分辨率当作误差范围
//            if (isPointOnSegment(point, gf.createPoint(line.getCoordinates()[0]), gf.createPoint(line.getCoordinates()[1]), resolution)) {
//                System.out.println(point.getCoordinate() + " on line");
//            } else {
//                System.err.println(point.getCoordinate() + " not on line");
//            }
//        }
//    }
}
