package com.xhn.sys.service;

import com.xhn.sys.bean.Sources;
import com.xhn.sys.bean.SourcesExample;
import com.xhn.sys.mapper.SourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {

    @Autowired
    private SourcesMapper sourcesMapper;

    public List<Sources> getSourceByPid(int i) {
        SourcesExample example=new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        //通过id精确查询出对应的顶级菜单
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }

    public void saveInfo(Sources sources) {
        sourcesMapper.insertSelective(sources);
    }

    public Sources getsourcesById(Integer sid) {

        return sourcesMapper.selectByPrimaryKey(sid);

    }

    public void updateInfo(Sources sources) {
       sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    public void delete(Integer id) {
        sourcesMapper.deleteByPrimaryKey(id);
    }
}
