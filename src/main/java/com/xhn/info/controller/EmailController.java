package com.xhn.info.controller;

import com.xhn.info.bean.Email;
import com.xhn.info.service.EmailService;
import com.xhn.jobs.EmailJob;
import com.xhn.sys.bean.Employee;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private EmailService emailService;
    //添加操作
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Email email, HttpSession session) throws Exception {

        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).withIdentity("job1", "group1").build();
        JobDataMap dataMap = job.getJobDataMap();
        dataMap.put("email",email);
        dataMap.put("javaMailSenderImpl",javaMailSender);
        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(new Date()).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        dataMap.put("sched",sched);
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
         sched.scheduleJob(job, trigger);
        //启动定时任务管理器
        sched.start();

        //获取当前用户
        Employee userLogin=(Employee)session.getAttribute("userLogin");
        //获取当前用户的id
        Integer eid = userLogin.getEid();
        //保存到数据库
        //将当前用户的id保存到email中
        email.setEmpFk(eid);
        emailService.saveInfo(email);

        return "redirect:/message.jsp";
    }
}
