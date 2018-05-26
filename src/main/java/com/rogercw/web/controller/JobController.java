package com.rogercw.web.controller;

import com.rogercw.po.Job;
import com.rogercw.service.JobService;
import com.rogercw.service.JobService;
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
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    @RequestMapping("/findJobByParam")
    public ModelAndView findJobByParams(Job job, Integer pageIndex){
        ModelAndView mv=new ModelAndView();
        PageModel pageModel=new PageModel();
        if (pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Job> jobList=jobService.findJobByParam(job,pageModel);
        mv.addObject("jobList",jobList);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("job/job");
        return mv;
    }

    @RequestMapping("/deleteJob")
    public String deleteJob(String ids){
        String[] idArray=ids.split(",");
        for (String id:idArray){
            jobService.deleteJob(Integer.parseInt(id));
        }
        return "redirect:/job/findJobByParam";
    }

    @RequestMapping("showAddJob")
    public String showAddJob(){
        return "job/showAddJob";
    }

    @RequestMapping(value = "/insertJob",method = RequestMethod.POST)
    public String insertJob(Job job){
        jobService.insertJob(job);
        return "redirect:/job/findJobByParam";
    }

    /**
     * 更新页面
     * @param id
     * @return
     */
    @RequestMapping("/showUpdateJob")
    public String showUpdateJob(@RequestParam("id") int id, Model model){
        //查询数据以便更新时回显数据
        Job job=jobService.findJobById(id);
        model.addAttribute(job);
        return "job/showUpdateJob";
    }

    @RequestMapping("/updateJob")
    public String updateJob(Job job){
        jobService.updateJob(job);
        return "redirect:/job/findJobByParam";
    }
}

