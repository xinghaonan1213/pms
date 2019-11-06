package com.xhn.usual.service;

import com.xhn.usual.bean.ForumPost;
import com.xhn.usual.mapper.ForumPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Autowired
    private ForumPostMapper forumPostMapper;

    public void saveInfo(ForumPost forumPost) {
        forumPostMapper.insert(forumPost);
    }
}
