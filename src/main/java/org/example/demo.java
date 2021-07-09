package org.example;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhanggh
 * @description: TODO
 * @date 2021/7/9 10:16
 */

public class demo {
    public static void main(String[] args) {
        JSONObject gaodeJson_parse = JSONObject.parseObject("{\"status\":\"1\",\"info\":\"OK\",\"infocode\":\"10000\",\"regeocode\":{\"formatted_address\":\"重庆市巴南区鱼洞街道云滨路\",\"addressComponent\":{\"country\":\"中国\",\"province\":\"重庆市\",\"city\":[],\"citycode\":\"023\",\"district\":\"巴南区\",\"adcode\":\"500113\",\"township\":\"鱼洞街道\",\"towncode\":\"500113001000\",\"neighborhood\":{\"name\":[],\"type\":[]},\"building\":{\"name\":[],\"type\":[]},\"streetNumber\":{\"street\":\"尚文大道\",\"number\":\"1398号\",\"location\":\"106.524850,29.345600\",\"direction\":\"东南\",\"distance\":\"743\"},\"businessAreas\":[[]]},\"pois\":[{\"id\":\"B0G267MU0C\",\"name\":\"浩创·半山溪谷(建设中)\",\"type\":\"商务住宅;住宅区;住宅小区\",\"tel\":[],\"direction\":\"东南\",\"distance\":\"95.8879\",\"location\":\"106.520630,29.350163\",\"address\":\"龙洲湾云滨路999号\",\"poiweight\":\"0.248293\",\"businessarea\":[]},{\"id\":\"B0FFK7M1N4\",\"name\":\"重庆南城巴川学校\",\"type\":\"科教文化服务;学校;中学\",\"tel\":\"023-86028888\",\"direction\":\"北\",\"distance\":\"246.635\",\"location\":\"106.519726,29.352837\",\"address\":\"龙洞湾大桥\",\"poiweight\":\"0.169767\",\"businessarea\":[]},{\"id\":\"B0FFIB1MSW\",\"name\":\"重庆市职业技术教育中心\",\"type\":\"科教文化服务;学校;职业技术学校\",\"tel\":[],\"direction\":\"东\",\"distance\":\"490.703\",\"location\":\"106.524427,29.348847\",\"address\":\"崇仁路(重庆教育管理学校)\",\"poiweight\":\"0.243766\",\"businessarea\":[]},{\"id\":\"B0FFIVWSAJ\",\"name\":\"集美锦湾(建设中)\",\"type\":\"商务住宅;住宅区;住宅小区\",\"tel\":[],\"direction\":\"北\",\"distance\":\"518.144\",\"location\":\"106.520803,29.355195\",\"address\":\"云滨路1257号\",\"poiweight\":\"0.195765\",\"businessarea\":[]},{\"id\":\"B0FFHO3I33\",\"name\":\"重庆教育管理学校(巴南校区)\",\"type\":\"科教文化服务;学校;职业技术学校\",\"tel\":\"023-62966474;023-62966370;023-62966110\",\"direction\":\"东\",\"distance\":\"552.018\",\"location\":\"106.525066,29.348749\",\"address\":\"龙洲湾街道箭滨二路1126号\",\"poiweight\":\"0.447774\",\"businessarea\":[]},{\"id\":\"B0FFIZ3DIP\",\"name\":\"重庆新城金樾府\",\"type\":\"商务住宅;住宅区;住宅小区\",\"tel\":\"023-88268888\",\"direction\":\"东北\",\"distance\":\"740.02\",\"location\":\"106.522523,29.356834\",\"address\":\"云创路50号\",\"poiweight\":\"0.196227\",\"businessarea\":[]},{\"id\":\"B0H63S9OBM\",\"name\":\"重庆财经学院巴南校区学术报告厅\",\"type\":\"科教文化服务;学校;高等院校\",\"tel\":[],\"direction\":\"东南\",\"distance\":\"784.619\",\"location\":\"106.526293,29.346417\",\"address\":\"210国道李家咀附近\",\"poiweight\":\"0.242865\",\"businessarea\":[]},{\"id\":\"B0HDYZR2KP\",\"name\":\"重庆财经学院巴南校区-学生宿舍楼\",\"type\":\"商务住宅;住宅区;宿舍\",\"tel\":[],\"direction\":\"东南\",\"distance\":\"810.532\",\"location\":\"106.525856,29.345602\",\"address\":\"尚文大道重庆财经学院巴南校区\",\"poiweight\":\"0.154873\",\"businessarea\":[]},{\"id\":\"B0HDYZRODO\",\"name\":\"重庆财经学院巴南校区学生高层宿舍\",\"type\":\"商务住宅;住宅区;宿舍\",\"tel\":[],\"direction\":\"东\",\"distance\":\"812.523\",\"location\":\"106.527429,29.347609\",\"address\":\"尚文大道906号\",\"poiweight\":\"0.154873\",\"businessarea\":[]},{\"id\":\"B0HGURGL5D\",\"name\":\"联通大厦\",\"type\":\"商务住宅;楼宇;商务写字楼\",\"tel\":[],\"direction\":\"北\",\"distance\":\"814.949\",\"location\":\"106.518826,29.357900\",\"address\":[],\"poiweight\":\"0.262915\",\"businessarea\":[]},{\"id\":\"B0G0HSGKNZ\",\"name\":\"重庆财经学院巴南校区\",\"type\":\"科教文化服务;学校;高等院校\",\"tel\":\"023-88968651\",\"direction\":\"东南\",\"distance\":\"866.272\",\"location\":\"106.526543,29.345516\",\"address\":\"龙洲湾街道尚文大道906号\",\"poiweight\":\"0.243488\",\"businessarea\":[]},{\"id\":\"B0FFHO6N08\",\"name\":\"西部人才产业园\",\"type\":\"商务住宅;产业园区;产业园区\",\"tel\":[],\"direction\":\"东\",\"distance\":\"876.757\",\"location\":\"106.528730,29.349414\",\"address\":\"尚文大道附近(重庆工商大学融智学院旁)\",\"poiweight\":\"0.184206\",\"businessarea\":[]}],\"roads\":[{\"id\":\"023H48F032037614930\",\"name\":\"云滨路\",\"direction\":\"西\",\"distance\":\"41.1189\",\"location\":\"106.52,29.3505\"},{\"id\":\"023H48F032037610586\",\"name\":\"龙洞湾大桥\",\"direction\":\"西\",\"distance\":\"112.076\",\"location\":\"106.521,29.3504\"},{\"id\":\"023H48F032037605466\",\"name\":\"箭滩二路\",\"direction\":\"西\",\"distance\":\"305.705\",\"location\":\"106.523,29.3496\"}],\"roadinters\":[{\"direction\":\"西\",\"distance\":\"317.559\",\"location\":\"106.523062,29.350463\",\"first_id\":\"023H48F032037605466\",\"first_name\":\"箭滩二路\",\"second_id\":\"023H48F032037610586\",\"second_name\":\"龙洞湾大桥\"}],\"aois\":[]}}");
//        JSONObject gaodeJson_parse = JSONObject.parseObject("{\"status\":\"1\",\"info\":\"OK\",\"infocode\":\"10000\",\"regeocode\":{\"formatted_address\":\"重庆市九龙坡区陶家镇兴强路\",\"addressComponent\":{\"country\":\"中国\",\"province\":\"重庆市\",\"city\":[],\"citycode\":\"023\",\"district\":\"九龙坡区\",\"adcode\":\"500107\",\"township\":\"陶家镇\",\"towncode\":\"500107109000\",\"neighborhood\":{\"name\":[],\"type\":[]},\"building\":{\"name\":[],\"type\":[]},\"streetNumber\":{\"street\":[],\"number\":[],\"direction\":[],\"distance\":[]},\"businessAreas\":[[]]},\"pois\":[],\"roads\":[{\"id\":\"023H48F0320351979\",\"name\":\"兴强路\",\"direction\":\"东\",\"distance\":\"319.941\",\"location\":\"106.375,29.369\"},{\"id\":\"023H48F032036601264\",\"name\":\"跳陶路\",\"direction\":\"西南\",\"distance\":\"777.961\",\"location\":\"106.385,29.3729\"},{\"id\":\"023H48F032035117\",\"name\":\"陶跳路\",\"direction\":\"东\",\"distance\":\"806.329\",\"location\":\"106.37,29.3721\"}],\"roadinters\":[{\"direction\":\"南\",\"distance\":\"990.02\",\"location\":\"106.374708,29.378201\",\"first_id\":\"023H48F032035117\",\"first_name\":\"陶跳路\",\"second_id\":\"023H48F032036601264\",\"second_name\":\"跳陶路\"}],\"aois\":[]}}");
        Object formatted_address = gaodeJson_parse.getJSONObject("regeocode").get("formatted_address");
        Object poiName = null;
        try{
            poiName = gaodeJson_parse.getJSONObject("regeocode").getJSONArray("pois").getJSONObject(0).get("name");
        }catch (Exception e){
            poiName = formatted_address;
        }

//        if (poiName == null) {
//            poiName = formatted_address;
//        } else
            if (poiName.toString().contains("建设中")) {
            poiName = gaodeJson_parse.getJSONObject("regeocode").getJSONArray("pois").getJSONObject(1).get("name");
        }
//        System.out.println(formatted_address);
        System.out.println(poiName);
    }
}
