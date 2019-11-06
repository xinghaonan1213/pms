package com.xhn.info.service;

import com.xhn.info.bean.Email;
import com.xhn.info.mapper.EmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    public void saveInfo(Email email) {
        emailMapper.insert(email);
    }
}
