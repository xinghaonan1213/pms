package com.xhn.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailTest {

    @Test
    public void test01() throws MessagingException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        //邮件对象
        MimeMessage message = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        //你得邮箱
        helper.setFrom("xhn121397442@163.com");
        //接收的邮箱
        helper.setTo("wf2501936780@163.com");
        //邮件标题
        helper.setSubject("我是你男朋友");
        //邮件内容
        helper.setText("<html><head></head><body><span style='color:deeppink'>小猪猪<img src=cid:identifie></span>,<h2>凤嘟嘟</h2></body></html>",true);

        // 添加两个附件（附件位置位于java-->resources目录)，可根据需要添加或修改
        //作为附件
        FileSystemResource file=new FileSystemResource(new File("/D:\\Desktop\\timg.jpg"));
        helper.addAttachment("kl.jpg", file);
        //作为图片内置视图
        FileSystemResource file2=new FileSystemResource(new File("/D:\\Desktop\\123.jpg"));
        helper.addInline("identifie",file2);
        //发送邮箱
        bean.send(message);
        System.out.println("简单邮件文本发送成功！");
    }
}
