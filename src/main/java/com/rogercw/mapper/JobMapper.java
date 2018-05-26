package com.rogercw.mapper;

import com.rogercw.mapper.provider.JobDynamicSQLProvider;
import com.rogercw.po.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.JOBTABLE;

/**
 * Created by 1 on 2018/5/25.
 * 用户Mapper
 */
public interface JobMapper {

    /**
     * 查询指定工作
     * @param id
     * @return
     */
    @Select("SELECT * FROM "+JOBTABLE +" WHERE id=#{id}")
    public Job findJobById(Integer id);


    /**
     * 查询所有工作
     * @return
     */
    @Select("SELECT * FROM "+JOBTABLE)
    public List<Job> findtAllJob();


    /**
     * 删除指定工作
     * @param id
     */
    @Delete("DELETE FROM "+JOBTABLE + " WHERE id=#{id}")
    public void deleteJobById(Integer id);


    /**
     * 动态插入工作
     * @param job
     */
    @InsertProvider(type = JobDynamicSQLProvider.class,method="insertJob")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertJob(Job job);


    /**
     * 根据条件动态查询
     * @param params
     * @return
     */
    @SelectProvider(type=JobDynamicSQLProvider.class,method="findByParams")
    public List<Job> findByParam(Map<String, Object> params);


    /**
     * 查询满足条件的的总工作数量
     * @param params
     * @return
     */
    @SelectProvider(type=JobDynamicSQLProvider.class,method="selectCounts")
    public int selectCounts(Map<String, Object> params);


    /**
     * 动态更新数据
     * @param job
     */
    @UpdateProvider(type=JobDynamicSQLProvider.class,method="updateJob")
    public void updateJob(Job job);
}
