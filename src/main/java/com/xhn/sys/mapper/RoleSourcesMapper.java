package com.xhn.sys.mapper;

import com.xhn.sys.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleSourcesMapper {

    void insert(@Param("rid") int roleid,@Param("sid") int id);

    List<Role> selectRoles();
}
