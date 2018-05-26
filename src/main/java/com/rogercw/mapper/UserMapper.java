package com.rogercw.mapper;

import static com.rogercw.util.constant.HrmConstants.USERTABLE;

import com.rogercw.mapper.provider.UserDynamicSQLProvider;
import com.rogercw.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2018/5/25.
 * 用户Mapper
 */
public interface UserMapper {

    /**
     * 根据用户名和密码查询用户
     * @param loginname
     * @param password
     * @return
     */
    @Select("SELECT * FROM "+ USERTABLE +" WHERE loginname=#{loginname} AND password=#{password}")
    public User findUserByNameAndPwd(@Param("loginname") String loginname,
                                     @Param("password") String password);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM "+USERTABLE +" WHERE id=#{id}")
    public User findUserById(Integer id);

    /**
     * 删除指定用户
     * @param id
     */
    @Delete("DELETE FROM "+USERTABLE + " WHERE id=#{id}")
    public void deleteUserById(Integer id);

    /***
     * 动态插入用户
     * @param user
     */
    @InsertProvider(type = UserDynamicSQLProvider.class,method="insertUser")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertUser(User user);

    /**
     * 根据条件查询,并进行分页处理
     * @param params
     * @return
     */
    @SelectProvider(type=UserDynamicSQLProvider.class,method="findByParams")
    public List<User> findByParam(Map<String,Object> params);

    /**
     * 查询满足条件的总用户数量
     * @param params
     * @return
     */
    @SelectProvider(type=UserDynamicSQLProvider.class,method="selectCounts")
    public int selectCounts(Map<String,Object> params);


    @UpdateProvider(type=UserDynamicSQLProvider.class,method="updateUser")
    public void updateUser(User user);
}
