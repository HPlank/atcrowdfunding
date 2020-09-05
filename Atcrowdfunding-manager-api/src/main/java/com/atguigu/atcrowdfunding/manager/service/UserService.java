package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Page;

import java.util.Map;

public interface UserService {


    User queryUserLogin(Map<String, Object> paramMap);

//    Page queryUserPage(Integer pageno, Integer pagesize);

    int savaUser(User user);

    Page queryUserPage(Map<String,Object> paramMap);

    int saveUser(User user);

    User getUserById(Integer id);

    int updateUser(User user);
}
