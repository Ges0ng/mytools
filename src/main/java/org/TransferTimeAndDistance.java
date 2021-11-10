package org;

import com.aliyun.odps.udf.UDF;

/**
 * @author zhanggh
 * @description: 换乘距离、时间计算
 * @date 2021/10/29 16:35
 */

public class TransferTimeAndDistance extends UDF {
    public String evaluate(String stageType, String stageDist, String stageTime) {
        String[] walkSplit = stageType.split(",");
        String[] num = stageDist.split(",");
        String[] times = stageTime.split(",");
        if (walkSplit.length != num.length || walkSplit.length != times.length) {
            return null;
        }

        int sum = 0;
        double time = 0;
        int transCount = 0;
        for (int i = 1; i < walkSplit.length - 1; i++) {
            if (walkSplit[i - 1].contains("公交") && "步行".equals(walkSplit[i]) && walkSplit[i + 1].contains("公交")) {
                transCount ++;
                sum += Integer.parseInt(num[i]);
                time += Double.parseDouble(times[i]);
            }
        }
        System.out.printf("换乘次数：%d次，换乘步行距离：%d米，换乘时间：%.2f分钟 \n", transCount, sum, time / 60);
        return transCount + "," + sum + "," + String.format("%.2f", (time / 60));
    }

    public static void main(String[] args) {
        String type = "步行,普通公交线路/普通公交线路,步行,循环微公交,步行,循环微公交";
        String distance = "247,13552/13445,300,4998,339,123";
        String time = "211,2301/2331,145,1748,290,123";

        TransferTimeAndDistance timeAndDistance = new TransferTimeAndDistance();
        String evaluate = timeAndDistance.evaluate(type, distance,time);
        System.out.println(evaluate);
    }
}
