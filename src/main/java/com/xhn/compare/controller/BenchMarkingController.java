package com.xhn.compare.controller;

import com.xhn.compare.bean.BenchMarking;
import com.xhn.compare.service.BenchMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/benchmarking")
public class BenchMarkingController {

    @Autowired
    private BenchMarkingService benchMarkingService;
    //实现显示功能，查询出对应的公司名称和营销额度
    @RequestMapping(value = "list/{year}",method = RequestMethod.GET)
    @ResponseBody
    public List<BenchMarking> getBenchList(@PathVariable("year") Integer year){
      List<BenchMarking> list =  benchMarkingService.getBenchList(year);
      return list;
    }
    //实现增加功能
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(BenchMarking benchMarking){
        benchMarkingService.saveInfo(benchMarking);
        return "redirect:/indexvalue-base.jsp";
    }
}
