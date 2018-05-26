package com.rogercw.service;

import com.rogercw.po.Document;
import com.rogercw.util.tag.PageModel;

import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
public interface DocumentService {

    /**
     * 根据id查询文件
     * @param id
     * @return
     */
    public Document findDocumentById(Integer id);


    /**
     * 根据条件查询并进行分页
     * @param document
     * @param pageModel
     * @return
     */
    public List<Document> findDocumentByParam(Document document, PageModel pageModel);

    /**
     * 删除文件
     * @param id
     */
    public void deleteDocument(Integer id);

    /**
     * 更新文件
     * @param document
     */
    public void updateDocument(Document document);

    /**
     * 添加文件
     * @param document
     */
    public void insertDocument(Document document);
}
