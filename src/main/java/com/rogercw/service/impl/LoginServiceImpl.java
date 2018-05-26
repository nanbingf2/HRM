package com.rogercw.service.impl;

import com.rogercw.mapper.UserMapper;
import com.rogercw.po.User;
import com.rogercw.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 1 on 2018/5/25.
 */
@Transactional
@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String loginname, String password) {
        User user = userMapper.findUserByNameAndPwd(loginname, password);
        return user;
    }
}
