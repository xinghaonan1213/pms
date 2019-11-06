package com.xhn.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringUtils {
    //将Map集合遍历获取其中的键和值
    public static String parseparamterMapToString(Map<String,Object> paramterMap){
        Set<Map.Entry<String, Object>> entries = paramterMap.entrySet();
        String str="";
        for ( Map.Entry<String,Object> entry:entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            str=str+"&"+"search_"+key+"="+value;
        }
        return str;
    }
    //该方法是将key键中的主要字段提取出来，剩下的接去掉（通过_找到）
    public static Map<String,Object> parseParameterMapToMyBatisMap(Map<String,Object> parameterMap){
        Map<String,Object> resultMap=new HashMap<String, Object>();
        Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
        for (Map.Entry<String,Object> entry:entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            if (key.contains("like")){
                key=key.substring(key.indexOf("_")+1);
                value="%"+value+"%";
            }
            resultMap.put(key,value);
        }
        return resultMap;
    }
}
