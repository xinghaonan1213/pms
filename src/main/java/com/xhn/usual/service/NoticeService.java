package com.xhn.usual.service;

import com.github.pagehelper.PageInfo;
import com.xhn.usual.bean.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    void saveInfo(Notice notice);

    PageInfo<Notice> getNoticeList(Integer pageNum, Map<String, Object> parameterMap);

    List<Notice> getLatesNoticeList();


    Notice info(Integer nid);
}
