package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User queryUserLogin(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    List<User> queryList(Map<String, Object> paramMap);

//    int deleteBatchUserByVO(Data data);

    int deleteBatchUserByVO(List<User> userList);

    List<Role> queryAllRole();

    List<Integer> queryRoleById(Integer id);

    int saveUserRoleRelationship(@Param("userid") Integer userid, @Param("data")Data data);

    int deleteUserRoleRelationship(@Param("userid") Integer userid,@Param("data") Data data);

    List<Permission> queryPermissionByUserid(Integer id);
}