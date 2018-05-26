package com.rogercw.service.impl;

import com.rogercw.mapper.DocumentMapper;
import com.rogercw.po.Document;
import com.rogercw.service.DocumentService;
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
public class DocumentServiceImpl implements DocumentService{

    @Resource
    private DocumentMapper documentMapper;

    @Override
    public Document findDocumentById(Integer id) {
        return documentMapper.findDocumentById(id);
    }

    @Override
    public List<Document> findDocumentByParam(Document document, PageModel pageModel) {
        Map<String,Object> params=new HashMap<>();
        //查询总记录数
        int count=documentMapper.selectCounts(params);
        pageModel.setRecordCount(count);
        params.put("document",document);
        params.put("pageModel",pageModel);
        List<Document> documentList = documentMapper.findByParam(params);
        return documentList;
    }

    @Override
    public void deleteDocument(Integer id) {
        documentMapper.deleteDocumentById(id);
    }

    @Override
    public void updateDocument(Document document) {
        documentMapper.updateDocument(document);
    }

    @Override
    public void insertDocument(Document document) {
        documentMapper.insertDocument(document);
    }
}
