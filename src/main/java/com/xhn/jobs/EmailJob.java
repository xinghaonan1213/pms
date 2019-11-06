package com.xhn.jobs;

import com.xhn.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
       Email email=(Email)jobDataMap.get("email");
        JavaMailSenderImpl senderImpl = (JavaMailSenderImpl)jobDataMap.get("javaMailSenderImpl");
        Scheduler sched=(Scheduler) jobDataMap.get("sched");
        MimeMessage message = senderImpl.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true,"UTF-8");
            //你得邮箱
            helper.setFrom("xhn121397442@163.com");
            //要接收的邮箱
            helper.setTo(email.getEname());
            //邮件标题
            helper.setSubject(email.getTitle());
            //邮件内容
            helper.setText(email.getContent());

            //发送邮箱
            senderImpl.send(message);
            System.out.println("简单邮件文本发送成功！");
            sched.shutdown(true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
