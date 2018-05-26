package com.rogercw.service.impl;

import com.rogercw.mapper.JobMapper;
import com.rogercw.po.Job;
import com.rogercw.po.User;
import com.rogercw.service.JobService;
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
public class JobServiceImpl implements JobService{

    @Resource
    private JobMapper jobMapper;

    @Override
    public Job findJobById(Integer id) {
        return jobMapper.findJobById(id);
    }

    @Override
    public List<Job> findAllJob() {
        return jobMapper.findtAllJob();
    }

    @Override
    public List<Job> findJobByParam(Job job, PageModel pageModel) {
        Map<String,Object> params=new HashMap<>();
        //查询总记录数
        int count=jobMapper.selectCounts(params);
        pageModel.setRecordCount(count);
        params.put("job",job);
        params.put("pageModel",pageModel);
        List<Job> jobList = jobMapper.findByParam(params);
        return jobList;
    }

    @Override
    public void deleteJob(Integer id) {
        jobMapper.deleteJobById(id);
    }

    @Override
    public void updateJob(Job job) {
        jobMapper.updateJob(job);
    }

    @Override
    public void insertJob(Job job) {
        jobMapper.insertJob(job);
    }
}
