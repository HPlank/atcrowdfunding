package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import com.atguigu.atcrowdfunding.vo.Data;


import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> pageQuery(Map<String, Object> paramMap);

    int queryCount(Map<String, Object> paramMap);

    Role getRole(Integer id);

    int update(Role user);

    int delete(Integer uid);

    int batchDelete(Integer[] uid);

    int batchDeleteObj(Data datas);

    List<Role> queryAllRole();

    List<Integer> queryRoleidByUserid(Integer id);

    void saveUserRole(Integer userid, List<Integer> ids);

    void deleteUserRole(Integer userid, List<Integer> ids);

    int insertRolePermission(RolePermission rp);

    void deleteRolePermissionRelationship(Integer roleid);
}