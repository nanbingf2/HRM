package com.rogercw.mapper.provider;

import static com.rogercw.util.constant.HrmConstants.USERTABLE;

import com.rogercw.po.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by 1 on 2018/5/25.
 */
public class UserDynamicSQLProvider {

    /**
     * 动态插入
     * @return
     */
    public String insertUser(User user){
        return new SQL(){
            {
                INSERT_INTO(USERTABLE);
                if (user.getLoginname() != null && !user.getLoginname().equals("")) {
                    VALUES(" loginname","#{loginname}");
                }
                if (user.getPassword() != null && !user.getPassword().equals("")) {
                    VALUES(" password","#{password}");
                }
                if (user.getUsername() != null && !user.getUsername().equals("")) {
                    VALUES(" username","#{username}");
                }
                if (user.getStatus() != null) {
                    VALUES(" status","#{status}");
                }
                if (user.getCreateDate() != null) {
                    VALUES(" createdate","#{createDate}");
                }
            }
        }.toString();
    }


    /**
     * 根据条件动态查询并分页
     * @param params
     * @return
     */
    public String findByParams(Map<String,Object> params){
        User user= (User) params.get("user");
        String sql= new SQL(){
            {
                SELECT("*");
                FROM(USERTABLE);
                if (user.getUsername() != null && !user.getUsername().equals("")) {
                    WHERE(" username LIKE CONCAT('%',#{user.username},'%') ");
                }
                if (user.getStatus() != null){
                    WHERE(" status=#{user.status}");
                }
            }
        }.toString();

        //分页处理
        if (params.get("pageModel")!=null){
            sql=sql+" limit #{pageModel.firstPage} , #{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 查询满足条件的总用户数量
     * @param params
     * @return
     */
    public String selectCounts(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("COUNT(*)");
                FROM(USERTABLE);
                if (params.get("user") != null) {
                    User user= (User) params.get("user");
                    if (user.getUsername() != null && !user.getUsername().equals("")) {
                        WHERE(" username LIKE %#{user.username}%");
                    }
                    if (user.getStatus() != null){
                        WHERE(" status=#{user.status}");
                    }
                }
            }
        }.toString();
    }

    /**
     * 动态更新用户
     * @param user
     * @return
     */
    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE(USERTABLE);
                if (user.getLoginname() != null&&user.getLoginname()!="") {
                    SET(" loginname = #{loginname}");
                }
                if (user.getPassword() != null&&user.getPassword()!="") {
                    SET(" password = #{password}");
                }
                if (user.getUsername() != null&&user.getUsername()!="") {
                    SET(" username = #{username}");
                }
                if (user.getStatus() != null) {
                    SET(" status = #{status}");
                }
                WHERE(" id=#{id}");
            }
        }.toString();
    }
}
