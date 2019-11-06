package com.xhn.pro.service;

import com.xhn.pro.bean.Attachment;
import com.xhn.pro.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;


    public void saveInfo(Attachment atta) {
        attachmentMapper.insert(atta);
    }
}
