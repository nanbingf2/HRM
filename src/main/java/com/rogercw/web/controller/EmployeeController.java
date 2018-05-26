package com.rogercw.web.controller;

import com.rogercw.po.Dept;
import com.rogercw.po.Employee;
import com.rogercw.po.Job;
import com.rogercw.service.DeptService;
import com.rogercw.service.EmployeeService;
import com.rogercw.service.JobService;
import com.rogercw.util.tag.PageModel;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
@Controller()
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private DeptService deptService;
    @Resource
    private JobService jobService;

    @RequestMapping("/findEmployeeByParam")
    public ModelAndView findEmployeeByParams(Employee employee,
                   Integer pageIndex,Integer dept_id,Integer job_id){
        //设置相关的部门与工作
        this.setDeptAndJob(employee,dept_id,job_id);
        ModelAndView mv=new ModelAndView();
        PageModel pageModel=new PageModel();
        if (pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Employee> employeeList=employeeService.findEmployeeByParam(employee,pageModel);

        //查询所有的部门与工作,以便过滤查询
        List<Dept> depts=deptService.findAllDept();
        List<Job> jobs=jobService.findAllJob();

        mv.addObject("employeeList",employeeList);
        mv.addObject("pageModel",pageModel);
        mv.addObject("jobs",jobs);
        mv.addObject("depts",depts);
        mv.setViewName("employee/employee");
        return mv;
    }

    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(String ids){
        String[] idArray=ids.split(",");
        for (String id:idArray){
            employeeService.deleteEmployee(Integer.parseInt(id));
        }
        return "redirect:/employee/findEmployeeByParam";
    }

    @RequestMapping("showAddEmployee")
    public String showAddEmployee(Model model){
        //查询所有部门和员工信息,供用户选择
        List<Dept> depts=deptService.findAllDept();
        List<Job> jobs=jobService.findAllJob();
        model.addAttribute("depts",depts);
        model.addAttribute("jobs",jobs);
        return "employee/showAddEmployee";
    }

    @RequestMapping(value = "/insertEmployee",method = RequestMethod.POST)
    public String insertEmployee(Employee employee,Integer dept_id,Integer job_id){
        this.setDeptAndJob(employee,dept_id,job_id);
        employeeService.insertEmployee(employee);
        return "redirect:/employee/findEmployeeByParam";
    }

    /**
     * 更新页面
     * @param id
     * @return
     */
    @RequestMapping("/showUpdateEmployee")
    public String showUpdateEmployee(@RequestParam("id") int id, Model model,
                                     Integer dept_id,Integer job_id){
        //查询数据以便更新时回显数据
        Employee employee=employeeService.findEmployeeById(id);
        List<Dept> depts=deptService.findAllDept();
        List<Job> jobs=jobService.findAllJob();
        model.addAttribute("depts",depts);
        model.addAttribute("jobs",jobs);
        model.addAttribute("employee",employee);
        return "employee/showUpdateEmployee";
    }

    @RequestMapping("/updateEmployee")
    public String updateEmployee(Employee employee,Integer dept_id,Integer job_id){
        this.setDeptAndJob(employee,dept_id,job_id);
        employeeService.updateEmployee(employee);
        return "redirect:/employee/findEmployeeByParam";
    }

    private void setDeptAndJob(Employee employee,Integer dept_id,Integer job_id){
        if(job_id!=null){
            Job job=new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if(dept_id!=null){
            Dept dept=new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }
}

