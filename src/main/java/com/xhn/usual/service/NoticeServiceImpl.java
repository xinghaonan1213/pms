package com.xhn.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhn.common.StringUtils;
import com.xhn.usual.bean.Notice;
import com.xhn.usual.bean.NoticeExample;
import com.xhn.usual.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class  NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void saveInfo(Notice notice) {
        //前台传过来没有时间，需要自己添加
        notice.setNdate(new Date());
        noticeMapper.insert(notice);
    }

    public PageInfo<Notice> getNoticeList(Integer pageNum, Map<String, Object> parameterMap) {
        NoticeExample example=new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        Map<String, Object> myBatisMap = StringUtils.parseParameterMapToMyBatisMap(parameterMap);
        PageHelper.startPage(pageNum,3);
        List<Notice> notices = noticeMapper.selectByExample(example);
        PageInfo<Notice> page=new PageInfo<Notice>(notices,5);
        return page;
    }

    public List<Notice> getLatesNoticeList() {
        NoticeExample example=new NoticeExample();
         example.setOrderByClause("ndate desc limit 3");
       return noticeMapper.selectByExample(example);
    }

    public Notice info(Integer nid) {
        return noticeMapper.selectByPrimaryKey(nid);
    }
}
