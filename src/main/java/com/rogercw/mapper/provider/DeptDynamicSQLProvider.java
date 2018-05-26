package com.rogercw.mapper.provider;

import com.rogercw.po.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.DEPTTABLE;

/**
 * Created by 1 on 2018/5/25.
 */
public class DeptDynamicSQLProvider {

    /**
     * 动态插入
     * @param dept
     * @return
     */
    public String insertDept(Dept dept){
        return new SQL(){
            {
                INSERT_INTO(DEPTTABLE);
                if (dept.getRemark() != null && !dept.getRemark().equals("")) {
                    VALUES(" remark","#{remark}");
                }
                if (dept.getName() != null && !dept.getName().equals("")) {
                    VALUES(" name","#{name}");
                }
            }
        }.toString();
    }

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    public String findByParams(Map<String,Object> params){
        String sql= new SQL(){
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if (params.get("dept")!=null){
                    Dept dept= (Dept) params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{dept.name},'%') ");
                    }
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
     * 查询满足条件的总工作数
     * @param params
     * @return
     */
    public String selectCounts(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("COUNT(*)");
                FROM(DEPTTABLE);
                if (params.get("dept")!=null){
                    Dept dept= (Dept) params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
    }

    /**
     * 动态更新
     * @param dept
     * @return
     */
    public String updateDept(Dept dept){
        return new SQL(){
            {
                UPDATE(DEPTTABLE);
                if (dept.getName() != null && dept.getName()!="") {
                    SET(" name = #{name}");
                }
                if (dept.getRemark() != null) {
                    SET(" remark = #{remark}");
                }
                WHERE(" id=#{id}");
            }
        }.toString();
    }
}
