package com.xhn.common;

import java.util.HashMap;
import java.util.Map;

public class ResultEntity {
   public Map<String,Object> map=new HashMap<String, Object>();
    public ResultEntity put(String str,Object obj){
        this.map.put(str,obj);
        return this;
    }
    public static ResultEntity success(){
        ResultEntity entity=new ResultEntity();
        entity.map.put("statusCode",200);
        entity.map.put("message","响应成功");
        return entity;
    }
    public static ResultEntity error(){
        ResultEntity entity=new ResultEntity();
        entity.map.put("statusCode",500);
        entity.map.put("message","响应失败");
        return entity;
    }
}
