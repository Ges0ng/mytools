package odps.utils;

import com.aliyun.odps.udf.UDF;

/**
 * @author zhanggh
 * @description: 坐标系转换
 * @type 1:wgs84togcj02 2:gcj02towgs84 3:bd09togcj02
 * @date 2022/4/26 10:49
 */

public class CoordinateTransformUtil extends UDF {

    static double x_pi;
    static double pi;
    static double a;
    static double ee;

    static {
        CoordinateTransformUtil.x_pi = 52.35987755982988;
        CoordinateTransformUtil.pi = 3.141592653589793;
        CoordinateTransformUtil.a = 6378245.0;
        CoordinateTransformUtil.ee = 0.006693421622965943;
    }

    public static double[] bd09towgs84(final double lng, final double lat) {
        final double[] gcj = bd09togcj02(lng, lat);
        final double[] wgs84 = gcj02towgs84(gcj[0], gcj[1]);
        return wgs84;
    }

    public static double[] wgs84tobd09(final double lng, final double lat) {
        final double[] gcj = wgs84togcj02(lng, lat);
        final double[] bd09 = gcj02tobd09(gcj[0], gcj[1]);
        return bd09;
    }

    public static double[] gcj02tobd09(final double lng, final double lat) {
        final double z = Math.sqrt(lng * lng + lat * lat) + 2.0E-5 * Math.sin(lat * CoordinateTransformUtil.x_pi);
        final double theta = Math.atan2(lat, lng) + 3.0E-6 * Math.cos(lng * CoordinateTransformUtil.x_pi);
        final double bd_lng = z * Math.cos(theta) + 0.0065;
        final double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[]{bd_lng, bd_lat};
    }

    public static double[] bd09togcj02(final double bd_lon, final double bd_lat) {
        final double x = bd_lon - 0.0065;
        final double y = bd_lat - 0.006;
        final double z = Math.sqrt(x * x + y * y) - 2.0E-5 * Math.sin(y * CoordinateTransformUtil.x_pi);
        final double theta = Math.atan2(y, x) - 3.0E-6 * Math.cos(x * CoordinateTransformUtil.x_pi);
        final double gg_lng = z * Math.cos(theta);
        final double gg_lat = z * Math.sin(theta);
        return new double[]{gg_lng, gg_lat};
    }

    public static double[] wgs84togcj02(final double lng, final double lat) {
        if (out_of_china(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        final double radlat = lat / 180.0 * CoordinateTransformUtil.pi;
        double magic = Math.sin(radlat);
        magic = 1.0 - CoordinateTransformUtil.ee * magic * magic;
        final double sqrtmagic = Math.sqrt(magic);
        dlat = dlat * 180.0 / (CoordinateTransformUtil.a * (1.0 - CoordinateTransformUtil.ee) / (magic * sqrtmagic) * CoordinateTransformUtil.pi);
        dlng = dlng * 180.0 / (CoordinateTransformUtil.a / sqrtmagic * Math.cos(radlat) * CoordinateTransformUtil.pi);
        final double mglat = lat + dlat;
        final double mglng = lng + dlng;
        return new double[]{mglng, mglat};
    }

    public static double[] gcj02towgs84(final double lng, final double lat) {
        if (out_of_china(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        final double radlat = lat / 180.0 * CoordinateTransformUtil.pi;
        double magic = Math.sin(radlat);
        magic = 1.0 - CoordinateTransformUtil.ee * magic * magic;
        final double sqrtmagic = Math.sqrt(magic);
        dlat = dlat * 180.0 / (CoordinateTransformUtil.a * (1.0 - CoordinateTransformUtil.ee) / (magic * sqrtmagic) * CoordinateTransformUtil.pi);
        dlng = dlng * 180.0 / (CoordinateTransformUtil.a / sqrtmagic * Math.cos(radlat) * CoordinateTransformUtil.pi);
        final double mglat = lat + dlat;
        final double mglng = lng + dlng;
        return new double[]{lng * 2.0 - mglng, lat * 2.0 - mglat};
    }

    public static double transformlat(final double lng, final double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * CoordinateTransformUtil.pi) + 20.0 * Math.sin(2.0 * lng * CoordinateTransformUtil.pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * CoordinateTransformUtil.pi) + 40.0 * Math.sin(lat / 3.0 * CoordinateTransformUtil.pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * CoordinateTransformUtil.pi) + 320.0 * Math.sin(lat * CoordinateTransformUtil.pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformlng(final double lng, final double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * CoordinateTransformUtil.pi) + 20.0 * Math.sin(2.0 * lng * CoordinateTransformUtil.pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * CoordinateTransformUtil.pi) + 40.0 * Math.sin(lng / 3.0 * CoordinateTransformUtil.pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * CoordinateTransformUtil.pi) + 300.0 * Math.sin(lng / 30.0 * CoordinateTransformUtil.pi)) * 2.0 / 3.0;
        return ret;
    }

    public static boolean out_of_china(final double lng, final double lat) {
        return lng < 72.004 || lng > 137.8347 || (lat < 0.8293 || lat > 55.8271);
    }

    public static void printArray(final Object[] arrs) {
        if (arrs == null || arrs.length == 0) {
            System.out.println("null");
        }
        System.out.print(arrs[0]);
        for (int i = 1; i < arrs.length; ++i) {
            System.out.print("," + arrs[i]);
        }
        System.out.println();
    }

    public static String doubleArrayToStr(final double[] arrs, final String delimiter) {
        if (arrs == null || arrs.length == 0) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(arrs[0]);
        for (int i = 1; i < arrs.length; ++i) {
            sb.append(delimiter);
            sb.append(arrs[i]);
        }
        return sb.toString();
    }

    public static String doubleArrayToStr(final double[] arrs) {
        return doubleArrayToStr(arrs, ",");
    }

    public static void printArray(final double[] arrs) {
        if (arrs == null || arrs.length == 0) {
            System.out.println("null");
        }
        System.out.print(arrs[0]);
        for (int i = 1; i < arrs.length; ++i) {
            System.out.print("," + arrs[i]);
        }
        System.out.println();
    }

    public String evaluate(final Double lng, final Double lat, final Long type) {
        if (type == 1L) {
            return CoordinateTransformUtil.doubleArrayToStr(CoordinateTransformUtil.wgs84togcj02(lng, lat));
        }
        if (type == 2L) {
            return CoordinateTransformUtil.doubleArrayToStr(CoordinateTransformUtil.gcj02towgs84(lng, lat));
        }
        return CoordinateTransformUtil.doubleArrayToStr(CoordinateTransformUtil.wgs84togcj02(lng, lat));
    }

    public String evaluate(final String lng, final String lat, final String type) {
        final Double dlng = Double.valueOf(lng);
        final Double dlat = Double.valueOf(lat);
        final Long ltype = Long.valueOf(type);
        return this.evaluate(dlng, dlat, ltype);
    }

    public static void main(final String[] args) {
        CoordinateTransformUtil cos = new CoordinateTransformUtil();
        System.out.println(cos.evaluate(120.2829713, 30.18239608, 1L));
    }
}
