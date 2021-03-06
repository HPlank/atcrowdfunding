package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.service.RoleService;
import com.atguigu.atcrowdfunding.util.Page;


import com.atguigu.atcrowdfunding.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;



    public Page<Role> pageQuery(Map<String, Object> paramMap) {
        Page<Role> rolePage = new Page<Role>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));

        paramMap.put("startIndex", rolePage.getStartIndex());

        List<Role> roles = roleMapper.pageQuery(paramMap);

        // 获取数据的总条数
        int count = roleMapper.queryCount(paramMap);
        rolePage.setData(roles);
        rolePage.setTotalsize(count);
        return rolePage;
    }

    public int queryCount(Map<String, Object> paramMap) {
        return roleMapper.queryCount(paramMap);
    }

    public void saveRole(Role user) {
        roleMapper.insert(user);
    }

    public Role getRole(Integer id) {
        return roleMapper.getRole(id);
    }

    public int updateRole(Role user) {
        return roleMapper.update(user);
    }

    public int deleteRole(Integer uid) {
        return roleMapper.delete(uid);
    }

    public int batchDeleteRole(Integer[] uid) {
        return roleMapper.batchDelete(uid);
    }

    public int batchDeleteRole(Data datas) {
        return roleMapper.batchDeleteObj(datas);
    }

    public List<Role> queryAllRole() {
        return roleMapper.queryAllRole();
    }


    public List<Integer> queryRoleidByUserid(Integer id) {
        return roleMapper.queryRoleidByUserid(id);
    }

/*	@Override
	public void doAssignRoleByUserid(Integer userid, Integer[] ids) {
		roleDao.saveUserRole(userid,ids);
	}

	@Override
	public void doUnAssignRoleByUserid(Integer userid, Integer[] ids) {
		roleDao.deleteUserRole(userid,ids);
	}*/


    public void doAssignRoleByUserid(Integer userid, List<Integer> ids) {
        roleMapper.saveUserRole(userid,ids);
    }


    public void doUnAssignRoleByUserid(Integer userid, List<Integer> ids) {
        roleMapper.deleteUserRole(userid,ids);
    }


    public int saveRolePermissionRelationship(Integer roleid, Data datas) {

        roleMapper.deleteRolePermissionRelationship(roleid);

        int totalCount = 0 ;
        List<Integer> ids = datas.getIds();
        for (Integer permissionid : ids) {
            RolePermission rp = new RolePermission();
            rp.setRoleid(roleid);
            rp.setPermissionid(permissionid);
            int count = roleMapper.insertRolePermission(rp);
            totalCount += count ;
        }

        return totalCount;
    }

}
