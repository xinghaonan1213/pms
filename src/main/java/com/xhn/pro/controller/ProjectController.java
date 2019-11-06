package com.xhn.pro.controller;

import com.xhn.pro.bean.Project;
import com.xhn.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //添加新项目
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
         projectService.saveInfo(project);
         return "redirect:/project-base.jsp";
    }
    //显示所有的项目
    @RequestMapping(value = "/getProList",method = RequestMethod.GET)
    public ModelAndView getProList(){
       ModelAndView mv=new ModelAndView("project-base");
       List<Project> list= projectService.getProList();
       mv.addObject("list",list);
       return mv;
    }
    //通过搜索条件查询
    @RequestMapping(value = "/getProListSearch",method = RequestMethod.GET)
    public ModelAndView getProListSearch(Integer cid,String keyword,Integer orderby){
        ModelAndView mv=new ModelAndView("project-base");
        List<Project> list= projectService.getProListSearch(cid,keyword,orderby);
        mv.addObject("list",list);
        return mv;
    }
    //添加附件项目的下拉选择框(异步请求)
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProJsonList(){
        List<Project> list=projectService.getProJsonList();
        return list;
    }
}
