package com.xhn.usual.controller;

import com.xhn.sys.bean.Employee;
import com.xhn.usual.bean.ForumPost;
import com.xhn.usual.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/fatie")
public class ForumPostController {

    @Autowired
    private ForumPostService service;
    //添加完成重定向到index界面
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
     //添加内容放置到pojo对象中
    public String saveInfo(ForumPost forumPost, HttpSession session){
        //前台只传过来俩个数据 发帖标题和内容
        //需要传入的还有
        Employee userLogin=(Employee)session.getAttribute("userLogin");
        Integer eid = userLogin.getEid();
        forumPost.setEmpFk3(eid);
        forumPost.setCreatetime(new Date());
        service.saveInfo(forumPost);
        return "redirect:/main.jsp";
    }

}
