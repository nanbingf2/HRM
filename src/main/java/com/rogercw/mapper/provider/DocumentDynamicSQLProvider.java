package com.rogercw.mapper.provider;

import com.rogercw.po.Document;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.DOCUMENTTABLE;

/**
 * Created by 1 on 2018/5/25.
 */
public class DocumentDynamicSQLProvider {

    /**
     * 动态插入
     * @param document
     * @return
     */
    public String insertDocument(Document document){
        return new SQL(){
            {
                INSERT_INTO(DOCUMENTTABLE);
                if (document.getRemark() != null && !document.getRemark().equals("")) {
                    VALUES(" remark","#{remark}");
                }
                if (document.getFilename() != null && !document.getFilename().equals("")) {
                    VALUES(" filename","#{filename}");
                }
                if (document.getUser() != null && document.getUser().getId()!=0) {
                    VALUES(" user_id","#{user.id}");
                }
                if (document.getCreateDate() != null) {
                    VALUES(" create_date","#{createDate}");
                }
                if (document.getPath() != null) {
                    VALUES(" path","#{path}");
                }
            }
        }.toString();
    }

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    public String findByParams(Map<String,Object> params){
        String sql= new SQL(){
            {
                SELECT("*");
                FROM(DOCUMENTTABLE);
                if (params.get("document")!=null){
                    Document document= (Document) params.get("document");
                    if (document.getFilename() != null && !document.getFilename().equals("")) {
                        WHERE(" filename LIKE CONCAT('%',#{document.filename},'%') ");
                    }
                }
            }
        }.toString();

        //分页处理
        if (params.get("pageModel")!=null){
            sql=sql+" limit #{pageModel.firstPage} , #{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 查询满足条件的总工作数
     * @param params
     * @return
     */
    public String selectCounts(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("COUNT(*)");
                FROM(DOCUMENTTABLE);
                if (params.get("document")!=null){
                    Document document= (Document) params.get("document");
                    if (document.getFilename() != null && !document.getFilename().equals("")) {
                        WHERE(" filename LIKE CONCAT('%',#{document.filename},'%') ");
                    }
                }
            }
        }.toString();
    }

    /**
     * 动态更新
     * @param document
     * @return
     */
    public String updateDocument(Document document){
        return new SQL(){
            {
                UPDATE(DOCUMENTTABLE);
                if (document.getFilename() != null && document.getFilename()!="") {
                    SET(" filename = #{filename}");
                }
                if (document.getRemark() != null) {
                    SET(" remark = #{remark}");
                }
                if (document.getUser() != null&&document.getUser().getId()!=0) {
                    SET(" user_id = #{user.id}");
                }
                if (document.getPath() != null) {
                    SET(" path = #{path}");
                }
                WHERE(" id=#{id}");
            }
        }.toString();
    }
}
