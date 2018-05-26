package com.rogercw.service.impl;

import com.rogercw.mapper.DeptMapper;
import com.rogercw.po.Dept;
import com.rogercw.service.DeptService;
import com.rogercw.service.DeptService;
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
public class DeptServiceImpl implements DeptService{

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept findDeptById(Integer id) {
        return deptMapper.findDeptById(id);
    }

    @Override
    public List<Dept> findAllDept() {
        return deptMapper.findtAllDept();
    }

    @Override
    public List<Dept> findDeptByParam(Dept dept, PageModel pageModel) {
        Map<String,Object> params=new HashMap<>();
        //查询总记录数
        int count=deptMapper.selectCounts(params);
        pageModel.setRecordCount(count);
        params.put("dept",dept);
        params.put("pageModel",pageModel);
        List<Dept> deptList = deptMapper.findByParam(params);
        return deptList;
    }

    @Override
    public void deleteDept(Integer id) {
        deptMapper.deleteDeptById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateDept(dept);
    }

    @Override
    public void insertDept(Dept dept) {
        deptMapper.insertDept(dept);
    }
}
