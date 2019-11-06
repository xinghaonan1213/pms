package com.xhn.pro.service;

import com.xhn.pro.bean.Project;

import java.util.List;

public interface ProjectService {

    void saveInfo(Project project);

    List<Project> getProList();

    List<Project> getProListSearch(Integer cid, String keyword, Integer orderby);

    List<Project> getProJsonList();
}
