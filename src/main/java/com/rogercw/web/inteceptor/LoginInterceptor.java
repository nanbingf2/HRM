package com.rogercw.web.inteceptor;

import com.rogercw.po.User;
import com.rogercw.util.constant.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 2018/5/26.
 */
public class LoginInterceptor implements HandlerInterceptor{

    //不会拦截的路径
    private static final String[] IGNORE_PATH={"/loginForm","/404.html","/login","/logout"};


    //当该方法返回true时,会继续执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean flag=true;  //拦截标志,默认需要拦截

        //获取请求的路径
        String requestPath=request.getServletPath();
        for (String path:IGNORE_PATH){
            if(requestPath.contains(path)){
                //不需要拦截的路径
                flag=false;
            }
        }
        if(flag){
            //路径需要拦截
            //通过查看session中是否有user对象来判断是否已经登录
            User user= (User) request.getSession().getAttribute(HrmConstants.USER_SESSION);
            if (user != null) {
                //用户已经登录,不需要拦截
                flag=false;
            }else{
                //用户没有登录,返回到登录页面
                request.setAttribute("message","请先登录再访问该系统");
                request.getRequestDispatcher(HrmConstants.LOGIN).forward(request,response);
            }
        }
        return !flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
