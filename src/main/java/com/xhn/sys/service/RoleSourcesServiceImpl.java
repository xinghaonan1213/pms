package com.xhn.sys.service;

import com.xhn.sys.bean.Role;
import com.xhn.sys.mapper.RoleSourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleSourcesServiceImpl implements RoleSourcesService {

    @Autowired
    private RoleSourcesMapper roleSourcesMapper;

    public void saveInfo(int roleid, String ids) {
            //将ids切分
        String[] id = ids.split(",");
        //遍历数组
        for (String s : id) {
            roleSourcesMapper.insert(roleid,Integer.parseInt(s));
        }
    }
}
