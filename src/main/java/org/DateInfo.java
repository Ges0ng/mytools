package org;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author zhanggh
 * @description: 日期维表接口解析
 * @description: Api接口；http://timor.tech/api/holiday/year/2022?type=Y&week=Y
 * @date 2022/1/25 9:16
 */

public class DateInfo {
    //指定年份
    final static String YEAR = "2022";
    //指定输出文件名
    final static String FILE = "dwd_ts_bas_doe_date_info.txt";

    public static void main(String[] args) {
        //从api中获取数据
        String json = HttpUtil.get("http://timor.tech/api/holiday/year/" + YEAR + "?type=Y&week=Y");
        //解析接口获得的json
        String target = dateParse(json);
        //输出文件
        write(FILE,target);
    }


    /***
     * TODO 日期维表JSon解析
     * @param data
     * @return
     * {
     *   "code": 0,               // 0服务正常。-1服务出错
     *   "holiday": {
     *     "10-01": {
     *       "holiday": true,     // 该字段一定为true
     *       "name": "国庆节",     // 节假日的中文名。
     *       "wage": 3,           // 薪资倍数，3表示是3倍工资
     *       "date": "2018-10-01" // 节假日的日期
     *     },
     *     "10-02": {
     *       "holiday": true,     // 该字段一定为true
     *       "name": "国庆节",     // 节假日的中文名。
     *       "wage": 3,           // 薪资倍数，3表示是3倍工资
     *       "date": "2018-10-01" // 节假日的日期
     *     }
     *   },
     *   "type": {                     // 只有明确指定参数 type=Y 时才返回类型信息
     *     "2018-10-01": {             // 一一对应holiday对象的key，holiday有多少个这里就有多少个
     *       "type": enum(0, 1, 2, 3), // 节假日类型，分别表示 工作日、周末、节日、调休。
     *       "name": "周六",            // 节假日类型中文名，可能值为 周一 至 周日、假期的名字、某某调休。
     *       "week": enum(1 - 7)       // 一周中的第几天。值为 1 - 7，分别表示 周一 至 周日。
     *     }
     *   }
     * }
     */
    public static String dateParse(String data){
        StringBuilder sb = new StringBuilder();
        //拼接第一行的字段
        sb.append("stat_date"+"\t"+"date_type"+"\t"+"date_name"+"\t"+"day_cnt"+"\t"+"data_version"+"\n");
        JSONObject jsonObject = JSONObject.parseObject(data);
        JSONObject type = jsonObject.getJSONObject("type");
        //因为每一串json的key都对应着不同的日期，所以不能直接处理，需要放到Map中再处理
        for (Map.Entry<String, Object> entry : type.entrySet()) {
            //获取主键
            String stat_date = entry.getKey().replace("-","");
            //获取主键对应的value
            JSONObject value = (JSONObject) entry.getValue();
            Object date_type = value.get("type");
            Object date_name = value.get("name");
            //当月总天数
            int day_cnt = getDaysByYearMonth(2022, Integer.parseInt(stat_date.substring(4, 6).replace("0","")));

            sb.append(stat_date).append("\t")
                    .append(date_type).append("\t")
                    .append(date_name).append("\t")
                    .append(day_cnt).append("\t")
                    .append("2022").append("\n");
        }

        return sb.toString();
    }

    /***
     * 获取当月天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 打印内容到文件
     * @param content
     */
    private static void write(String filePath, String content) {
        FileWriter fileWriter = null;
        try {
            boolean fugai = false;
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fileWriter = new FileWriter(filePath, fugai);
            //打印内容
            fileWriter.write(content);
            System.out.println(new Date() + "：文件输出成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void write(String filePath, String content,boolean noCover) {
        FileWriter fileWriter = null;
        try {

            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fileWriter = new FileWriter(filePath, noCover);
            //打印内容
            fileWriter.write(content);
            System.out.println(new Date() + "：文件输出成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
