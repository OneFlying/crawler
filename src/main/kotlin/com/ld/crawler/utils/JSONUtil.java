package com.ld.crawler.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JSONUtil {

    public static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> jsonObject = JSON.parseObject(jsonStr);
        return jsonObject;
    }

    public static String mapToJsonStr(Map<String, Object> map) {
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        return itemJSONObj.toJSONString();
    }
}
