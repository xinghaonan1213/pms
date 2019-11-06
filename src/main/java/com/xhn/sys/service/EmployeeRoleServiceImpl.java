package com.xhn.sys.service;

import com.xhn.sys.mapper.EmployeeRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {
   @Autowired
    private EmployeeRoleMapper mapper;

    public void insert(int eid, String[] roleids) {
        for (String roleid : roleids) {
            mapper.insert(eid,Integer.parseInt(roleid));
        }
    }
}
