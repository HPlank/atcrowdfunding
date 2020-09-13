package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

import java.util.Map;

public interface RoleService {

    int saveRolePermissionRelationship(Integer roleid, Data datas);

    int batchDeleteRole(Data datas);

    int deleteRole(Integer uid);

    int updateRole(Role role);

    Role getRole(Integer id);

    void saveRole(Role role);

    Page pageQuery(Map<String, Object> paramMap);
}
