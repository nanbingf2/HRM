package com.rogercw.mapper;

import com.rogercw.mapper.provider.DocumentDynamicSQLProvider;
import com.rogercw.po.Document;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.DOCUMENTTABLE;

/**
 * Created by 1 on 2018/5/25.
 * 用户Mapper
 */
public interface DocumentMapper {

    /**
     * 查询指定文件
     * @param id
     * @return
     */
    @Select("SELECT * FROM "+DOCUMENTTABLE +" WHERE id=#{id}")
    public Document findDocumentById(Integer id);


    /**
     * 删除指定文件
     * @param id
     */
    @Delete("DELETE FROM "+DOCUMENTTABLE + " WHERE id=#{id}")
    public void deleteDocumentById(Integer id);


    /**
     * 动态插入文件
     * @param document
     */
    @InsertProvider(type = DocumentDynamicSQLProvider.class,method="insertDocument")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertDocument(Document document);


    /**
     * 根据条件动态查询
     * @param params
     * @return
     */
    @SelectProvider(type=DocumentDynamicSQLProvider.class,method="findByParams")
    @Results({
        @Result(id=true,property = "id",column = "id"),
            @Result(property = "filename",column = "filename"),
            @Result(property = "createDate",column = "create_date"),
            @Result(property = "path",column = "path"),
            @Result(property = "remark",column = "remark"),
            @Result(property = "user",column = "user_id",
                    one = @One(select = "com.rogercw.mapper.UserMapper.findUserById",
                                fetchType = FetchType.EAGER))
    })
    public List<Document> findByParam(Map<String, Object> params);


    /**
     * 查询满足条件的的总文件数量
     * @param params
     * @return
     */
    @SelectProvider(type=DocumentDynamicSQLProvider.class,method="selectCounts")
    public int selectCounts(Map<String, Object> params);


    /**
     * 动态更新数据
     * @param document
     */
    @UpdateProvider(type=DocumentDynamicSQLProvider.class,method="updateDocument")
    public void updateDocument(Document document);
}
