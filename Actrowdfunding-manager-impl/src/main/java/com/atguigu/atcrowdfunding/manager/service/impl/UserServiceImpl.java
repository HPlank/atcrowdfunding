package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.execption.LoginFailExecption;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
