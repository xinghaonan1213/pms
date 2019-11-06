package com.xhn.sys.service;

import com.xhn.sys.bean.Role;

import java.util.List;

public interface RoleService {
    int saveInfo(Role role);
    List<Role> getRoleList();
}
