package com.rogercw.web.controller;

import com.rogercw.po.Notice;
import com.rogercw.po.User;
import com.rogercw.service.NoticeService;
import com.rogercw.util.constant.HrmConstants;
import com.rogercw.util.tag.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
@Controller()
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @RequestMapping("/findNoticeByParam")
    public ModelAndView findNoticeByParams(Notice notice, Integer pageIndex){
        ModelAndView mv=new ModelAndView();
        PageModel pageModel=new PageModel();
        if (pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Notice> noticeList=noticeService.findNoticeByParam(notice,pageModel);
        mv.addObject("noticeList",noticeList);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("notice/notice");
        return mv;
    }

    @RequestMapping("/deleteNotice")
    public String deleteNotice(String ids){
        String[] idArray=ids.split(",");
        for (String id:idArray){
            noticeService.deleteNotice(Integer.parseInt(id));
        }
        return "redirect:/notice/findNoticeByParam";
    }

    @RequestMapping("showAddNotice")
    public String showAddNotice(){
        return "notice/showAddNotice";
    }

    @RequestMapping(value = "/insertNotice",method = RequestMethod.POST)
    public String insertNotice(Notice notice, HttpSession session){
        //从session中获取当前用户
        User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        notice.setUser(user);
        noticeService.insertNotice(notice);
        return "redirect:/notice/findNoticeByParam";
    }

    /**
     * 更新页面
     * @param id
     * @return
     */
    @RequestMapping("/showUpdateNotice")
    public String showUpdateNotice(@RequestParam("id") int id, Model model){
        //查询数据以便更新时回显数据
        Notice notice=noticeService.findNoticeById(id);
        model.addAttribute(notice);
        return "notice/showUpdateNotice";
    }

    @RequestMapping("/updateNotice")
    public String updateNotice(Notice notice){
        noticeService.updateNotice(notice);
        return "redirect:/notice/findNoticeByParam";
    }


    //预览处理
    @RequestMapping("/previewNotice")
    public String previewNotice(Integer id,Model model){
        Notice notice=noticeService.findNoticeById(id);
        model.addAttribute(notice);
        return "notice/previewNotice";
    }
}

