package com.rogercw.service;

import com.rogercw.po.User;

/**
 * Created by 1 on 2018/5/25.
 */
public interface LoginService {
    /**
     * 登录操作
     * @param loginname
     * @param password
     * @return
     */
    public User login(String loginname,String password);


}
