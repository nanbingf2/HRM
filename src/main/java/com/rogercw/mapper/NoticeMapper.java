package com.rogercw.mapper;

import com.rogercw.mapper.provider.NoticeDynamicSQLProvider;
import com.rogercw.po.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.NOTICETABLE;

/**
 * Created by 1 on 2018/5/25.
 * 用户Mapper
 */
public interface NoticeMapper {

    /**
     * 查询指定通知
     * @param id
     * @return
     */
    @Select("SELECT * FROM "+NOTICETABLE +" WHERE id=#{id}")
    public Notice findNoticeById(Integer id);


    /**
     * 查询所有通知
     * @return
     */
    @Select("SELECT * FROM "+NOTICETABLE)
    public List<Notice> findtAllNotice();


    /**
     * 删除指定通知
     * @param id
     */
    @Delete("DELETE FROM "+NOTICETABLE + " WHERE id=#{id}")
    public void deleteNoticeById(Integer id);


    /**
     * 动态插入通知
     * @param notice
     */
    @InsertProvider(type = NoticeDynamicSQLProvider.class,method="insertNotice")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertNotice(Notice notice);


    /**
     * 根据条件动态查询
     * @param params
     * @return
     */
    @SelectProvider(type=NoticeDynamicSQLProvider.class,method="findByParams")
    @Results({
        @Result(id = true,column = "id",property = "id"),
        @Result(column = "title",property = "title"),
        @Result(column = "createDate",property = "create_date"),
        @Result(column = "user_id",property = "user",
                one = @One(select = "com.rogercw.mapper.UserMapper.findUserById",
                        fetchType = FetchType.EAGER))
    })
    public List<Notice> findByParam(Map<String, Object> params);


    /**
     * 查询满足条件的的总通知数量
     * @param params
     * @return
     */
    @SelectProvider(type=NoticeDynamicSQLProvider.class,method="selectCounts")
    public int selectCounts(Map<String, Object> params);


    /**
     * 动态更新数据
     * @param notice
     */
    @UpdateProvider(type=NoticeDynamicSQLProvider.class,method="updateNotice")
    public void updateNotice(Notice notice);
}
