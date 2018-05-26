package com.rogercw.web.controller;

import com.rogercw.po.Dept;
import com.rogercw.service.DeptService;
import com.rogercw.util.tag.PageModel;
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
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @RequestMapping("/findDeptByParam")
    public ModelAndView findDeptByParams(Dept dept, Integer pageIndex){
        ModelAndView mv=new ModelAndView();
        PageModel pageModel=new PageModel();
        if (pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Dept> deptList=deptService.findDeptByParam(dept,pageModel);
        mv.addObject("deptList",deptList);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("dept/dept");
        return mv;
    }

    @RequestMapping("/deleteDept")
    public String deleteDept(String ids){
        String[] idArray=ids.split(",");
        for (String id:idArray){
            deptService.deleteDept(Integer.parseInt(id));
        }
        return "redirect:/dept/findDeptByParam";
    }

    @RequestMapping("showAddDept")
    public String showAddDept(){
        return "dept/showAddDept";
    }

    @RequestMapping(value = "/insertDept",method = RequestMethod.POST)
    public String insertDept(Dept dept){
        deptService.insertDept(dept);
        return "redirect:/dept/findDeptByParam";
    }

    /**
     * 更新页面
     * @param id
     * @return
     */
    @RequestMapping("/showUpdateDept")
    public String showUpdateDept(@RequestParam("id") int id, Model model){
        //查询数据以便更新时回显数据
        Dept dept=deptService.findDeptById(id);
        model.addAttribute(dept);
        return "dept/showUpdateDept";
    }

    @RequestMapping("/updateDept")
    public String updateDept(Dept dept){
        deptService.updateDept(dept);
        return "redirect:/dept/findDeptByParam";
    }
}

