package com.rogercw.web.controller;

import com.rogercw.po.User;
import com.rogercw.service.LoginService;
import com.rogercw.util.constant.HrmConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 1 on 2018/5/24.
 */
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 动态跳转页面
     * @param path
     * @return
     */
    @RequestMapping("{path}")
    public String pathUI(@PathVariable("path") String path){
        return path;
    }

    /**
     * 登录操作
     * @param loginname
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(String loginname, String password,
                              HttpSession session){
        User user = loginService.login(loginname, password);
        ModelAndView mv=new ModelAndView();
        if (user != null) {
            //将用户保存到session中
            session.setAttribute(HrmConstants.USER_SESSION, user);
            //将当前用户添加到ModelAndView对象中
            mv.addObject(user);
            mv.setViewName("redirect:/main");
        }else{
            mv.addObject("message","登录名或者密码错误,请重新登录");
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //从session中清除当前用户
        session.removeAttribute(HrmConstants.USER_SESSION);
        return "redirect:/loginForm";
    }
}
