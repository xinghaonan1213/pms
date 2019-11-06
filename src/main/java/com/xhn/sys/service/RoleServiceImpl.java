package com.xhn.sys.service;

import com.xhn.sys.bean.Role;
import com.xhn.sys.bean.RoleExample;
import com.xhn.sys.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public int saveInfo(Role role) {
        roleMapper.saveInfo(role);
        return role.getRoleid();
    }

    public List<Role> getRoleList() {
        return roleMapper.selectByExample(new RoleExample());
    }
}
