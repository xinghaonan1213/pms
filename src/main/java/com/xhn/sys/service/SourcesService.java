package com.xhn.sys.service;

import com.xhn.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getSourceByPid(int i);

    void saveInfo(Sources sources);

    Sources getsourcesById(Integer sid);

    void updateInfo(Sources sources);

    void delete(Integer id);
}
