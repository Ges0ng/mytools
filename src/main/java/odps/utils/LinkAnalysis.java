package odps.utils;

import com.aliyun.odps.udf.UDF;

public class LinkAnalysis extends UDF {
    public Integer evaluate(String stage_type,Integer count) {
        int flag=0;

        String[] split = stage_type.split(",");
        for (int i = 0; i < split.length-1; i++) {
            if (split[i].contains("公交")&&i+1<=split.length-1) {
                if (split[i+1].contains("公交")){
                    flag += 1;
                    if (flag == count) {
                        return 0;
                    }
                }
                else if (i+2<=split.length-1){
                    if (split[i+1].contains("步行")&&split[i+2].contains("公交")){
                        flag+=1;
                        if (flag==count){
                            return i+2;
                        }
                    }
                }
            }
        }
        return 1000;
    }
    public static void main(String[] args) {
        String a1="步行,公交线路,公交线路,步行,公交线路,步行";
        String a2="步行,普通公交线路,普通公交线路,步行,普通公交线路,步行";
        int evaluate = new LinkAnalysis().evaluate(a2, 2);
        System.out.println(evaluate);
    }
}