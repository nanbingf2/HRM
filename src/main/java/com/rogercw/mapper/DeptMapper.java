package com.rogercw.mapper;

import com.rogercw.mapper.provider.DeptDynamicSQLProvider;
import com.rogercw.po.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.DEPTTABLE;

/**
 * Created by 1 on 2018/5/25.
 * 用户Mapper
 */
public interface DeptMapper {

    /**
     * 查询指定部门
     * @param id
     * @return
     */
    @Select("SELECT * FROM "+DEPTTABLE +" WHERE id=#{id}")
    public Dept findDeptById(Integer id);


    /**
     * 查询所有部门
     * @return
     */
    @Select("SELECT * FROM "+DEPTTABLE)
    public List<Dept> findtAllDept();


    /**
     * 删除指定部门
     * @param id
     */
    @Delete("DELETE FROM "+DEPTTABLE + " WHERE id=#{id}")
    public void deleteDeptById(Integer id);


    /**
     * 动态插入工作
     * @param dept
     */
    @InsertProvider(type = DeptDynamicSQLProvider.class,method="insertDept")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertDept(Dept dept);


    /**
     * 根据条件动态查询
     * @param params
     * @return
     */
    @SelectProvider(type=DeptDynamicSQLProvider.class,method="findByParams")
    public List<Dept> findByParam(Map<String, Object> params);


    /**
     * 查询满足条件的的总工作数量
     * @param params
     * @return
     */
    @SelectProvider(type=DeptDynamicSQLProvider.class,method="selectCounts")
    public int selectCounts(Map<String, Object> params);


    /**
     * 动态更新数据
     * @param dept
     */
    @UpdateProvider(type=DeptDynamicSQLProvider.class,method="updateDept")
    public void updateDept(Dept dept);
}
