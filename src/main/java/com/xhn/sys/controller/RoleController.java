package com.xhn.sys.controller;

import com.xhn.common.ResultEntity;
import com.xhn.sys.bean.Role;
import com.xhn.sys.service.RoleService;
import com.xhn.sys.service.RoleSourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

   @Autowired
    private RoleService roleService;
   @Autowired
   private RoleSourcesService roleSourcesService;
    //添加角色
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role, String ids){
        //向角色表添加数据并返回一个角色的id
       int roleid= roleService.saveInfo(role);
        //将角色的id和权限资源的id添加到角色和权限资源的关联表中
        roleSourcesService.saveInfo(roleid,ids);
        return ResultEntity.success();
    }
    //查询出所有的角色信息
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoleList(){
        return  roleService.getRoleList();
    }
}
