package org;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class regionUtil2 {
    /**
     * 判断是否在多边形区域内
     *
     * @param point           要判断的点
     * @param pointHeight     要判断的点的高度
     * @param points          多边形区域各顶点的数组
     * @param ponitsMinHeight 多边形区域最小高度
     * @param ponitsMaxHeight 多边形区域最大高度
     * @return boolean
     */
    public static boolean isInPolygon(Point point, Integer pointHeight, List<Point> points, Integer ponitsMinHeight, Integer ponitsMaxHeight) {


        // todo 要判断的点的高度小于多边形区域最小高度 或者 要判断的点的高度大于于多边形区域最大高度
  /*  if (ponitsMinHeight > pointHeight || ponitsMaxHeight < pointHeight) {
      return false;
    }*/
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point1 = new Point2D.Double(point.getY(), point.getX());
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        double polygonPointx = 0.0, polygonPointy = 0.0;
        // 区域各顶点的纵坐标数组
        List<Double> lon = new ArrayList<>();
        // 区域各顶点的横坐标数组
        List<Double> lat = new ArrayList<>();
        for (Point point2 : points) {
            lon.add(point2.getY());
            lat.add(point2.getX());
        }
/*    points.parallelStream().forEach(p -> {
      lon.add(p.getY());
      lat.add(p.getX());
    });*/
        for (int i = 0; i < lon.size(); i++) {
            polygonPointx = lon.get(i);
            polygonPointy = lat.get(i);
            Point2D.Double polygonPoint = new Point2D.Double(polygonPointx, polygonPointy);
            pointList.add(polygonPoint);
        }
        return check(point1, pointList);
    }

    /**
     * 一个点是否在多边形内
     *
     * @param point   要判断的点的横纵坐标
     * @param polygon 组成的顶点坐标集合
     * @return
     */
    private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath peneralPath = new java.awt.geom.GeneralPath();

        Point2D.Double first = polygon.get(0);
        // 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
        peneralPath.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            // 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
            peneralPath.lineTo(d.x, d.y);
        }
        // 将几何多边形封闭
        peneralPath.lineTo(first.x, first.y);
        peneralPath.closePath();
        // 测试指定的 Point2D 是否在 Shape 的边界内。
        return peneralPath.contains(point);
    }
}
