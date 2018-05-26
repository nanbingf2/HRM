package com.rogercw.web.controller;

import com.rogercw.po.User;
import com.rogercw.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/findUserByParam")
    public ModelAndView findUserByParams(User user,Integer pageIndex){
        ModelAndView mv=new ModelAndView();
        PageModel pageModel=new PageModel();
        if (pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        List<User> userList=userService.findUserByParams(user,pageModel);
        mv.addObject("userList",userList);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("user/user");
        return mv;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(String ids){
        String[] idArray=ids.split(",");
        for (String id:idArray){
            userService.deleteUser(Integer.parseInt(id));
        }
        return "redirect:/user/findUserByParam";
    }

    @RequestMapping("showAddUser")
    public String showAddUser(){
        return "user/showAddUser";
    }

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public String insertUser(User user){
        userService.insertUser(user);
        return "redirect:/user/findUserByParam";
    }

    /**
     * 更新页面
     * @param id
     * @return
     */
    @RequestMapping("/showUpdateUser")
    public String showUpdateUser(@RequestParam("id") int id, Model model){
        //查询数据以便更新时回显数据
        User user=userService.findUserById(id);
        model.addAttribute(user);
        return "user/showUpdateUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/findUserByParam";
    }
}
