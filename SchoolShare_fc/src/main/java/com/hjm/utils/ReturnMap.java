package com.hjm.utils;

import java.util.HashMap;
import java.util.Map;

public class ReturnMap {
    public static Map<String, Object> success(Object o){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", o);
        return map;
    }
    public static Map<String, Object> error(int code, String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", null);
        return map;
    }
}
