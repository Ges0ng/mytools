package org.example;

import com.aliyun.odps.udf.UDF;

/**
 * @author zhanggh
 * @description: TODO
 * @date 2021/7/7 22:09
 */

public class GetWalkDistance extends UDF {

    public String evaluate(String walk,String distance) {

        String[] walkSplit = walk.split(",");
        String[] num = distance.split(",");
        int sum = 0;

        if (walkSplit.length != num.length) {
            return null;
        }
        for (int i = 0; i < walkSplit.length; i++) {
            if ("步行".equals(walkSplit[i])) {
                sum += Integer.parseInt(num[i]);
            }
        }
        return Integer.toString(sum);
    }

    public static void main(String[] args) {
        String foot = "步行,普通公交线路,普通公交线路/普通公交线路,步行";
        String distance = "194,3081,9848/9815,138";
        GetWalkDistance getWalkDistance = new GetWalkDistance();
        String evaluate = getWalkDistance.evaluate(foot, distance);
        System.out.println(evaluate);
    }
}
