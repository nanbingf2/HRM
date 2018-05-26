package com.rogercw.service;

import com.rogercw.po.Dept;
import com.rogercw.util.tag.PageModel;

import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
public interface DeptService {

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public Dept findDeptById(Integer id);

    /**
     * 查询所有部门
     * @return
     */
    public List<Dept> findAllDept();

    /**
     * 根据条件查询并进行分页
     * @param dept
     * @param pageModel
     * @return
     */
    public List<Dept> findDeptByParam(Dept dept, PageModel pageModel);

    /**
     * 删除部门
     * @param id
     */
    public void deleteDept(Integer id);

    /**
     * 更新部门
     * @param dept
     */
    public void updateDept(Dept dept);

    /**
     * 添加部门
     * @param dept
     */
    public void insertDept(Dept dept);
}
