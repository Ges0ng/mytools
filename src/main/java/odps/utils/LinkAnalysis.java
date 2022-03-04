package odps.utils;

import com.aliyun.odps.udf.UDF;

/**
 * 杨泽翰
 * 参数:出行链路 String,第几次换乘 int
 * 返回:第几次换乘的步行的下标
 *
 */
public class LinkAnalysis extends UDF {
    public Integer evaluate(String stage_type,Integer count) {
        int flag=0;
        //切割公交出行链条
        String[] split = stage_type.split(",");
        //循环数组
        for (int i = 0; i < split.length-1; i++) {
            //判断当前链条是否包含公交
            if (split[i].contains("公交")&&i+1<=split.length-1) {
             //特殊情况比如普通公交,普通公交连在一起则判断返回0
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
        //示例
        String a1="步行,公交线路,公交线路,步行,公交线路,步行";
        String a2="步行,普通公交线路,步行,普通公交线路,步行";
        int evaluate = new LinkAnalysis().evaluate(a2, 1);
        System.out.println(evaluate);
    }
}