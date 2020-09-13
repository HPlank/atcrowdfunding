package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.execption.LoginFailExecption;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.Page;


import com.atguigu.atcrowdfunding.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserLogin(Map<String, Object> paramMap) {
        User user = userMapper.queryUserLogin(paramMap);

        if(user == null){
            throw new LoginFailExecption("账号或密码有误");
        }

        return user;
    }

//    public Page queryUserPage(Integer pageno, Integer pagesize) {
//        Page page = new Page(pageno,pagesize);
//
//        Integer startIndex = page.getStartIndex();
//        List<User> datas = userMapper.queryList(startIndex,pagesize);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+datas);
//        page.setData(datas);
//
//        Integer totalsize = userMapper.queryCount();
//        System.out.println(totalsize);
//        page.setTotalsize(totalsize);
//
//        return page;
//    }

    public int savaUser(User user) {
        return userMapper.insert(user);
    }

    public Page queryUserPage(Map<String,Object> paramMap) {


        Page page = new Page((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);

        List<User> datas = userMapper.queryList(paramMap);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+datas);
        page.setData(datas);

        Integer totalsize = userMapper.queryCount(paramMap);
        System.out.println(totalsize);
        page.setTotalsize(totalsize);

        return page;
    }

    public int saveUser(User user) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date data = new Date();

        String createtime = sdf.format(data);

        user.setCreatetime(createtime);

        user.setUserpswd(MD5Util.digest(Const.PASSWORD));

        int result = userMapper.insert(user);

        return result;
    }

    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int deleteBatchUser(Integer[] ids) {
        int totalCont = 0;
        for (Integer id:ids){
            int count = userMapper.deleteByPrimaryKey(id);
            totalCont += count;
        }
        if(totalCont!=ids.length){
            throw new RuntimeException("批量删除失败！");
        }
        return totalCont;
    }

//    public int deleteBatchUserByVO(Data data) {
//        return userMapper.deleteBatchUserByVO(data);
//    }

    public int deleteBatchUserByVO(Data data) {
        return userMapper.deleteBatchUserByVO(data.getDatas());
    }

    public List<Role> queryAllRole() {
        return userMapper.queryAllRole();
    }

    public List<Integer> queryRoleById(Integer id) {
        return userMapper.queryRoleById(id);
    }

    public int saveUserRoleRelationship(Integer userid, Data data) {
        return userMapper.saveUserRoleRelationship(userid,data);
    }

    public int deleteUserRoleRelationship(Integer userid, Data data) {
        return userMapper.deleteUserRoleRelationship(userid,data);
    }

    public List<Permission> queryPermissionByUserid(Integer id) {
        return userMapper.queryPermissionByUserid(id);
    }

}
