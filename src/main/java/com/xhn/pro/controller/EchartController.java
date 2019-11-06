package com.xhn.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/echart")
public class EchartController {
    @RequestMapping(value = "/getEcharts",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEcharts(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("衬衫",5);
        map.put("羊毛衫",20);
        map.put("雪纺衫",36);
        map.put("裤子",10);
        map.put("高跟鞋",10);
        map.put("袜子",20);
        return map;
    }
}
