package cn.edu.hust.dao;

import cn.edu.hust.model.HustDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lxiao on 2017/3/30.
 */
@Mapper
public interface HustDepartmentDao {
    @Select(" SELECT department_id, department, remarks FROM hust_department")
    List<HustDepartment> getAllDepartments();
}
