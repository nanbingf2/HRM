package com.rogercw.service;

import com.rogercw.po.Notice;
import com.rogercw.util.tag.PageModel;

import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
public interface NoticeService {

    /**
     * 根据id查询通知
     * @param id
     * @return
     */
    public Notice findNoticeById(Integer id);


    /**
     * 根据条件查询并进行分页
     * @param notice
     * @param pageModel
     * @return
     */
    public List<Notice> findNoticeByParam(Notice notice, PageModel pageModel);

    /**
     * 删除通知
     * @param id
     */
    public void deleteNotice(Integer id);

    /**
     * 更新通知
     * @param notice
     */
    public void updateNotice(Notice notice);

    /**
     * 添加通知
     * @param notice
     */
    public void insertNotice(Notice notice);
}
