package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public Permission getRootPermission() {
        return permissionMapper.getRootPermission();
    }

    public List<Permission> getChildrenPremissionById(Integer id) {
        return permissionMapper.getChildrenPremissionById(id);
    }

    public List<Permission> queryAllPermission() {
        return permissionMapper.queryAllPermission();
    }

    public int savePermission(Permission permission) {
        return permissionMapper.insert(permission);
    }

    public Permission getPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public int updatePermission(Permission permission) {
        return permissionMapper.updateByPrimaryKey(permission);
    }

    public int deletePermission(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    public List<Integer> queryPermissionByRoleid(Integer roleId) {
        return permissionMapper.queryPermissionByRoleid(roleId);
    }
}
