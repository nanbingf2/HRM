package com.rogercw.service;

import com.rogercw.po.Employee;
import com.rogercw.util.tag.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
@Service
@Transactional
public interface EmployeeService {

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    public Employee findEmployeeById(Integer id);


    /**
     * 根据条件查询并进行分页
     * @param employee
     * @param pageModel
     * @return
     */
    public List<Employee> findEmployeeByParam(Employee employee, PageModel pageModel);

    /**
     * 删除员工
     * @param id
     */
    public void deleteEmployee(Integer id);

    /**
     * 更新员工
     * @param employee
     */
    public void updateEmployee(Employee employee);

    /**
     * 添加员工
     * @param employee
     */
    public void insertEmployee(Employee employee);
}
