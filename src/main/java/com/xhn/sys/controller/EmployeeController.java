package com.xhn.sys.controller;

import com.xhn.sys.bean.Employee;
import com.xhn.sys.service.EmployeeRoleService;
import com.xhn.sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRoleService roleService;

    //实现用户的添加功能
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee employee,String[] roleids){
      //与昂应标中添加数据，并将对应的eid返回
        int eid= employeeService.saveInfo(employee);
        //将返回的eid和roleid添加到中间表中
        roleService.insert(eid,roleids);
        return "redirect:/user.jsp";
    }

    //获取除本用户以外的其他用户进行数据回显
    @RequestMapping(value = "/getReceiver",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getReceiver(HttpSession session){
        //获取当前用户
        Employee userLogin=(Employee)session.getAttribute("userLogin");
        //获取当前用户的id
        Integer eid = userLogin.getEid();
        //查询
        List<Employee> employees=employeeService.getReceiver(eid);
         return employees;
    }


    //实现登出功能
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }
    //重定向想携带参数
    //1.需要在目标方法参数上添加RedirectAttributes类型的参数
    //调用RedirectAttributes对象的addFlashAttributes()方法
    //2.不能直接重定向
    //实现登陆界面
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        //登陆完成后重定向到主界面
        //先获取后台存储的验证码
        String validateCode = (String) session.getAttribute("validateCode");
       //将前台传过来的验证码与后台的验证码比较
        if (code.equalsIgnoreCase(validateCode)){
            //验证码成功进行比对账号和密码
            employee=employeeService.login(employee);
            //比对完成后清空session域存储，转而存储登陆信息
            session.removeAttribute("validateCode");
            //比对成功
            if (employee != null){
                //存储登陆信息
                session.setAttribute("userLogin",employee);
                //比对成功 重定向到主界面
                return "redirect:/index.jsp";
            }else {
                //添加错误信息
                attributes.addFlashAttribute("errorMessage","用户名或密码错误");
                //比对失败 重定向到登陆界面
                return "redirect:/login";
            }
        }
        //比对失败 重定向到登陆界面
        attributes.addFlashAttribute("errorMessage","验证码错误");
        return "redirect:/login";
    }

    @RequestMapping(value = "/mangers",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmpList() {
        return employeeService.getEmpList();
    }
}