package odps.utils;

import com.aliyun.odps.udf.UDF;

/**
 * @author yanghzehan
 * 将出行链路的所有时间或者距离聚合.
 * 入参:出行链路时间/距离
 * 返回:时间总和/距离总和
 */
public class StageAll extends UDF {

    public Integer evaluate(String Stage) {
        Integer I=0;
        String[] split = Stage.split(",");
        for (String S:split) {
           if (S.contains("/")==true){
               String[] split1 = S.split("/");
               S=split1[0];
           }
           I+=Integer.valueOf(S);
        }

        return I;
    }

    public static void main(String[] args) {
        StageAll stageAll=new StageAll();
        System.out.println(stageAll.evaluate("1527,2101,68/900"));
    }
}