package com.rogercw.mapper.provider;

import com.rogercw.po.Employee;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.rogercw.util.constant.HrmConstants.EMPLOYEETABLE;

/**
 * Created by 1 on 2018/5/25.
 */
public class EmployeeDynamicSQLProvider {

    /**
     * 动态插入
     * @param employee
     * @return
     */
    public String insertEmployee(Employee employee){
        return new SQL(){
            {
                INSERT_INTO(EMPLOYEETABLE);
                if (employee.getName() != null && !employee.getName().equals("")) {
                    VALUES(" name","#{name}");
                }
                if (employee.getAddress() != null && !employee.getAddress().equals("")) {
                    VALUES(" address","#{address}");
                }
                if (employee.getCard_id() != null && !employee.getCard_id().equals("")) {
                    VALUES(" card_id","#{card_id}");
                }
                if (employee.getSex() != null && !employee.getSex().equals("")) {
                    VALUES(" sex","#{sex}");
                }
                if (employee.getBirthday() != null && !employee.getBirthday().equals("")) {
                    VALUES(" birthday","#{birthday}");
                }
                if (employee.getPhone() != null && !employee.getPhone().equals("")) {
                    VALUES(" phone","#{phone}");
                }
                if (employee.getJob() != null) {
                    VALUES(" dept_id","#{dept.id}");
                }
                if (employee.getJob() != null) {
                    VALUES(" job_id","#{job.id}");
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
                FROM(EMPLOYEETABLE);
                if (params.get("employee")!=null){
                    Employee employee= (Employee) params.get("employee");
                    if (employee.getName() != null && !employee.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{employee.name},'%') ");
                    }
                    if (employee.getCard_id() != null && !employee.getCard_id().equals("")) {
                        WHERE(" card_id LIKE CONCAT('%',#{employee.card_id},'%') ");
                    }
                    if(employee.getDept()!=null && employee.getDept().getId()!=null&&employee.getDept().getId()!=0){
                        WHERE(" dept_id=#{employee.dept.id}");
                    }
                    if(employee.getJob()!=null && employee.getJob().getId()!=null&&employee.getJob().getId()!=0){
                        WHERE(" job_id=#{employee.job.id}");
                    }
                    if (employee.getSex() != null&& !employee.getSex().equals("")) {
                        WHERE(" sex=#{employee.sex}");
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
                FROM(EMPLOYEETABLE);
                if (params.get("employee")!=null){
                    Employee employee= (Employee) params.get("employee");
                    if (employee.getName() != null && !employee.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{employee.name},'%') ");
                    }
                    if (employee.getCard_id() != null && !employee.getCard_id().equals("")) {
                        WHERE(" card_id LIKE CONCAT('%',#{employee.card_id},'%') ");
                    }
                    if(employee.getDept()!=null && employee.getDept().getId()!=null&&employee.getDept().getId()!=0){
                        WHERE(" dept_id=#{employee.dept.id}");
                    }
                    if(employee.getJob()!=null && employee.getJob().getId()!=null&&employee.getJob().getId()!=0){
                        WHERE(" job_id=#{employee.job.id}");
                    }
                    if (employee.getSex() != null) {
                        WHERE(" sex=#{employee.sex}");
                    }
                }
            }
        }.toString();
    }

    /**
     * 动态更新
     * @param employee
     * @return
     */
    public String updateEmployee(Employee employee){
        return new SQL(){
            {
                UPDATE(EMPLOYEETABLE);
                if (employee.getName() != null && employee.getName()!="") {
                    SET(" name = #{name}");
                }
                if (employee.getCard_id() != null) {
                    SET(" card_id = #{card_id}");
                }
                if (employee.getAddress() != null) {
                    SET(" address = #{address}");
                }
                if (employee.getSex() != null) {
                    SET(" sex = #{sex}");
                }if (employee.getBirthday() != null) {
                    SET(" birthday = #{birthday}");
                }
                if (employee.getPhone() != null) {
                    SET(" phone = #{phone}");
                }
                if (employee.getDept() != null) {
                    SET(" dept_id = #{dept.id}");
                }
                if (employee.getJob() != null) {
                    SET(" job_id = #{job.id}");
                }
                WHERE(" id=#{id}");
            }
        }.toString();
    }
}
