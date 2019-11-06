package com.xhn.usual.controller;

import com.github.pagehelper.PageInfo;
import com.xhn.common.ResultEntity;
import com.xhn.common.StringUtils;
import com.xhn.usual.bean.Notice;
import com.xhn.usual.service.NoticeService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ResponseBody
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public Notice info(@PathVariable("id") Integer nid){

       return noticeService.info(nid);
    }

    //返回三条最新的数据
    @RequestMapping(value = "/latesNoticeList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getLatesNoticeList(){
       List<Notice> notices= noticeService.getLatesNoticeList();
       return ResultEntity.success().put("notices",notices);
    }

    @RequestMapping(value = "/getNoticeList",method =RequestMethod.GET )
    @ResponseBody
    //实现异步刷新实现分页和查询数据
    public ResultEntity getNoticeList(HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue ="1" ) Integer pageNum){
        //map的key是浏览器传过来的键去掉之后剩下的那部分
        //map的value是浏览器传过来的参数的值
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parseparamterMapToString(parameterMap);
        String requestURI = request.getRequestURI();
       PageInfo<Notice> page= noticeService.getNoticeList(pageNum,parameterMap);
       return ResultEntity.success().put("page",page).put("queryStr",queryStr).put("requestURI",requestURI);
    }

   /* //实现显示主界面
    @RequestMapping(value = "/getNoticeList",method =RequestMethod.GET )
    @ResponseBody
    public ResultEntity getNoticeList(){
        List<Notice> list=noticeService.getNoticeList();
       return ResultEntity.success().put("list",list);
    }*/
    //实现添加功能
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return ResultEntity.success();
    }
}

