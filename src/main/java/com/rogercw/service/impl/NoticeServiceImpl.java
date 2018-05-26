package com.rogercw.service.impl;

import com.rogercw.mapper.NoticeMapper;
import com.rogercw.po.Notice;
import com.rogercw.service.NoticeService;
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
public class NoticeServiceImpl implements NoticeService{

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeMapper.findNoticeById(id);
    }

    @Override
    public List<Notice> findNoticeByParam(Notice notice, PageModel pageModel) {
        Map<String,Object> params=new HashMap<>();
        //查询总记录数
        int count=noticeMapper.selectCounts(params);
        pageModel.setRecordCount(count);
        params.put("notice",notice);
        params.put("pageModel",pageModel);
        List<Notice> noticeList = noticeMapper.findByParam(params);
        return noticeList;
    }

    @Override
    public void deleteNotice(Integer id) {
        noticeMapper.deleteNoticeById(id);
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }

    @Override
    public void insertNotice(Notice notice) {
        noticeMapper.insertNotice(notice);
    }
}
