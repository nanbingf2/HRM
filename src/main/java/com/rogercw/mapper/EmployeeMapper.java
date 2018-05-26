package com.rogercw.mapper;

import com.rogercw.mapper.provider.EmployeeDynamicSQLProvider;
import com.rogercw.po.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.EMPLOYEETABLE;

/**
 * Created by 1 on 2018/5/26.
 */
public interface EmployeeMapper {

    /**
     * 查询指定员工
     * @param id
     * @return
     */
    @Select("SELECT * FROM "+EMPLOYEETABLE +" WHERE id=#{id}")
    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "name",column = "name"),
        @Result(property = "address",column = "address"),
        @Result(property = "age",column = "age"),
        @Result(property = "birthday",column = "birthday"),
        @Result(property = "card_id",column = "card_id"),
        @Result(property = "phone",column = "phone"),
        @Result(property = "dept",column = "dept_id",
                one=@One(select = "com.rogercw.mapper.DeptMapper.findDeptById",
                        fetchType = FetchType.EAGER)
        ),
            @Result(property = "job",column = "job_id",
                    one=@One(select = "com.rogercw.mapper.JobMapper.findJobById",
                    fetchType = FetchType.EAGER))
    })
    public Employee findEmployeeById(Integer id);


    /**
     * 删除指定员工
     * @param id
     */
    @Delete("DELETE FROM "+EMPLOYEETABLE + " WHERE id=#{id}")
    public void deleteEmployeeById(Integer id);


    /**
     * 动态新增员工
     * @param employee
     */
    @InsertProvider(type = EmployeeDynamicSQLProvider.class,method="insertEmployee")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertEmployee(Employee employee);


    /**
     * 根据条件动态查询
     * @param params
     * @return
     */
    @SelectProvider(type=EmployeeDynamicSQLProvider.class,method="findByParams")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "address",column = "address"),
            @Result(property = "age",column = "age"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "card_id",column = "card_id"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "dept",column = "dept_id",
                    one=@One(select = "com.rogercw.mapper.DeptMapper.findDeptById",
                            fetchType = FetchType.EAGER)
            ),
            @Result(property = "job",column = "job_id",
                    one=@One(select = "com.rogercw.mapper.JobMapper.findJobById",
                            fetchType = FetchType.EAGER))
    })
    public List<Employee> findByParam(Map<String, Object> params);


    /**
     * 查询满足条件的的总员工数量
     * @param params
     * @return
     */
    @SelectProvider(type=EmployeeDynamicSQLProvider.class,method="selectCounts")
    public int selectCounts(Map<String, Object> params);


    /**
     * 动态更新数据
     * @param employee
     */
    @UpdateProvider(type=EmployeeDynamicSQLProvider.class,method="updateEmployee")
    public void updateEmployee(Employee employee);
}
