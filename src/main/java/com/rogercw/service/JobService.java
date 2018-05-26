package com.rogercw.service;

import com.rogercw.po.Job;
import com.rogercw.util.tag.PageModel;

import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
public interface JobService {

    /**
     * 根据id查询工作
     * @param id
     * @return
     */
    public Job findJobById(Integer id);

    /**
     * 查询所有工作
     * @return
     */
    public List<Job> findAllJob();

    /**
     * 根据条件查询并进行分页
     * @param job
     * @param pageModel
     * @return
     */
    public List<Job> findJobByParam(Job job, PageModel pageModel);

    /**
     * 删除工作
     * @param id
     */
    public void deleteJob(Integer id);

    /**
     * 更新工作
     * @param job
     */
    public void updateJob(Job job);

    /**
     * 添加工作
     * @param job
     */
    public void insertJob(Job job);
}
