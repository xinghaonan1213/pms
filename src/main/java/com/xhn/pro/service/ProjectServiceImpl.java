package com.xhn.pro.service;

import com.xhn.pro.bean.Project;
import com.xhn.pro.bean.ProjectExample;
import com.xhn.pro.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    public List<Project> getProList() {
        List<Project> projects = projectMapper.getProList();
        return projects;
    }

    public List<Project> getProListSearch(Integer cid, String keyword, Integer orderby) {
        return projectMapper.getProListSearch(cid,keyword,orderby);
    }

    public List<Project> getProJsonList() {
        List<Project> list = projectMapper.selectByExample(new ProjectExample());
        return list;
    }
}
