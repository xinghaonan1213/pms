package com.xhn.sys.controller;

import com.xhn.common.ResultEntity;
import com.xhn.sys.bean.Sources;
import com.xhn.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {

    @Autowired
  private SourcesService sourcesService;

    //删除菜单资源
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity delete(Integer id){
        sourcesService.delete(id);
        return ResultEntity.success();
    }
    //修改对应的节点
    //1先实现数据的回填 查出对应pid下的数据
    @RequestMapping(value = "/getsourcesById/{id}",method = RequestMethod.GET)
    public ModelAndView getsourcesById(@PathVariable("id") Integer sid){
       Sources sources= sourcesService.getsourcesById(sid);
       ModelAndView mv=new ModelAndView("pm-edit");
       mv.addObject("onesource",sources);
       return  mv;
    }
    //实现页面的修改
    @RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public  String updateInfo(Sources sources){
        sourcesService.updateInfo(sources);
        return "redirect:/pm.jsp";
    }

    //添加资源(权限)
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public  String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }
    //查询菜单栏
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){

        List<Sources> list =sourcesService.getSourceByPid(0);

        //查询顶级菜单的二级菜单 list.get(0) 为顶级菜单
        queryChildren(list.get(0));

        return  list;
    }
    //递归方法获取每一级菜单
    private void queryChildren(Sources sources) {
        //查询二级菜单id
        Integer id = sources.getId();
        //查询对应的二级菜单
        List<Sources> source = sourcesService.getSourceByPid(id);
        //遍历二级菜单查询其他的下级菜单 递归
        for (Sources sources1 : source) {
            queryChildren(sources1);
        }
        //将二级菜单放置到顶级菜单中
        sources.setChildren(source);
    }
}
