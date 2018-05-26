package com.rogercw.mapper.provider;

import com.rogercw.po.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.NOTICETABLE;

/**
 * Created by 1 on 2018/5/25.
 */
public class NoticeDynamicSQLProvider {

    /**
     * 动态插入
     * @param notice
     * @return
     */
    public String insertNotice(Notice notice){
        return new SQL(){
            {
                INSERT_INTO(NOTICETABLE);
                if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                    VALUES(" title","#{title}");
                }
                if (notice.getCreateDate() != null) {
                    VALUES(" create_date","#{createDate}");
                }
                if (notice.getContent()!= null) {
                    VALUES(" content","#{content}");
                }
                if (notice.getUser()!=null&&notice.getUser().getId()!=null&&notice.getUser().getId()!=0){
                    VALUES(" user_id","#{user.id}");
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
                FROM(NOTICETABLE);
                if (params.get("notice")!=null){
                    Notice notice= (Notice) params.get("notice");
                    if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT('%',#{notice.title},'%') ");
                    }
                    if (notice.getContent() != null && !notice.getContent().equals("")) {
                        WHERE(" content LIKE CONCAT('%',#{notice.content},'%') ");
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
                FROM(NOTICETABLE);
                if(params.get("notice")!=null){
                    Notice notice= (Notice) params.get("notice");
                    if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT('%',#{notice.title},'%') ");
                    }
                    if (notice.getContent() != null && !notice.getContent().equals("")) {
                        WHERE(" content LIKE CONCAT('%',#{notice.content},'%') ");
                    }
                }
            }
        }.toString();
    }

    /**
     * 动态更新
     * @param notice
     * @return
     */
    public String updateNotice(Notice notice){
        return new SQL(){
            {
                UPDATE(NOTICETABLE);
                if (notice.getTitle() != null && notice.getTitle()!="") {
                    SET(" title = #{title}");
                }
                if (notice.getContent() != null) {
                    SET(" content = #{content}");
                }
                if (notice.getUser() != null&&notice.getUser().getId()!=0) {
                    SET(" user_id = #{user.id}");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
