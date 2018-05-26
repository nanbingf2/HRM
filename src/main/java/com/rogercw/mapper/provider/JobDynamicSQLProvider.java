package com.rogercw.mapper.provider;

import com.rogercw.po.Job;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.JOBTABLE;

/**
 * Created by 1 on 2018/5/25.
 */
public class JobDynamicSQLProvider {

    /**
     * 动态插入
     * @param job
     * @return
     */
    public String insertJob(Job job){
        return new SQL(){
            {
                INSERT_INTO(JOBTABLE);
                if (job.getName() != null && !job.getName().equals("")) {
                    VALUES(" name","#{name}");
                }
                if (job.getRemark() != null && !job.getRemark().equals("")) {
                    VALUES(" remark","#{remark}");
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
                FROM(JOBTABLE);
                if (params.get("job")!=null){
                    Job job= (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{job.name},'%') ");
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
                FROM(JOBTABLE);
                if (params.get("job")!=null){
                    Job job= (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{job.name},'%') ");
                    }
                }
            }
        }.toString();
    }

    /**
     * 动态更新
     * @param job
     * @return
     */
    public String updateJob(Job job){
        return new SQL(){
            {
                UPDATE(JOBTABLE);
                if (job.getName() != null&&job.getName()!="") {
                    SET(" name = #{name}");
                }
                if (job.getRemark() != null&&job.getRemark()!="") {
                    SET(" remark = #{remark}");
                }
                WHERE(" id=#{id}");
            }
        }.toString();
    }
}
