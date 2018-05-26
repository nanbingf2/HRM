package com.rogercw.service.impl;

import com.rogercw.mapper.EmployeeMapper;
import com.rogercw.po.Employee;
import com.rogercw.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService{

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeMapper.findEmployeeById(id);
    }

    @Override
    public List<Employee> findEmployeeByParam(Employee employee, PageModel pageModel) {
        Map<String,Object> params=new HashMap<>();
        //查询总记录数
        int count=employeeMapper.selectCounts(params);
        pageModel.setRecordCount(count);
        params.put("employee",employee);
        params.put("pageModel",pageModel);
        List<Employee> employeeList = employeeMapper.findByParam(params);
        return employeeList;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteEmployeeById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void insertEmployee(Employee employee) {
        employeeMapper.insertEmployee(employee);
    }
}
