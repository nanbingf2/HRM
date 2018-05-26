package com.rogercw.service;

import com.rogercw.po.User;
import com.rogercw.util.tag.PageModel;

import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
public interface UserService {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User findUserById(Integer id);

    /**
     * 根据条件查询并分页
     * @param user
     * @param pageModel
     * @return
     */
    public List<User> findUserByParams(User user, PageModel pageModel);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id);

    /**
     * 更新用户数据
     * @param user
     */
    public void updateUser(User user);

    /**
     * 插入用户数据
     * @param user
     */
    public void insertUser(User user);
}
