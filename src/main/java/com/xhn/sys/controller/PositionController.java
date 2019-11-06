package com.xhn.sys.controller;

import com.xhn.sys.bean.Position;
import com.xhn.sys.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    //查询出所有的数据
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Position> getPositionList(){
       return   positionService.getPositionList();
    }
}
