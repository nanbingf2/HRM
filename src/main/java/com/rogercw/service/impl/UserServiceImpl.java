package com.rogercw.service.impl;

import com.rogercw.po.User;
import com.rogercw.mapper.UserMapper;
import com.rogercw.service.UserService;
import com.rogercw.util.tag.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2018/5/25.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> findUserByParams(User user, PageModel pageModel) {
        Map<String,Object> params=new HashMap<>();
        //查询总记录数
        int count=userMapper.selectCounts(params);
        pageModel.setRecordCount(count);
        params.put("user",user);
        params.put("pageModel",pageModel);
        List<User> jobList = userMapper.findByParam(params);
        return jobList;
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
