package com.xhn.pro.controller;

import com.xhn.pro.bean.Attachment;
import com.xhn.pro.bean.Project;
import com.xhn.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/attach")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveInfo(Attachment atta, MultipartFile attachment, HttpSession session){
        //上传文件 1.获取绝对路径
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/upload");
        File file=new File(realPath);
        //判断文件夹是否存在
        if (!file.exists()){
            file.mkdirs();
        }
        //避免重名 获取文件名称
        String originalFilename = attachment.getOriginalFilename();
       String realName= UUID.randomUUID().toString().replaceAll("-","")+originalFilename;
        //  将文件传到对应的文件夹下
        try {
            attachment.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        atta.setPath(realPath+"/"+realName);
        attachmentService.saveInfo(atta);

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("statusCode",200);
        map.put("msg","上传成功！");
        return map;
    }
}
