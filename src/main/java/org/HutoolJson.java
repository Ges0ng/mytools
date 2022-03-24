package org;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * yangzehan
 * */
public class HutoolJson {
    public static void main(String[] args) {
        //json
        String jsonStr1 = "{\"id\": \"1\",\"name\": \"a\"}";
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr1);
        System.out.println(jsonObject.getByPath("name"));


//        jsonArray
        String jsonStr2 = "[{\"id\": \"1\",\"name\": \"a\"},{\"id\": \"2\",\"name\": \"b\"}]";
        JSONArray objects = JSONUtil.parseArray(jsonStr2);
        System.out.println(objects.getByPath("[0].id"));
    }

}

