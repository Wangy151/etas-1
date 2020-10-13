package cn.edu.hust.dao;

import cn.edu.hust.model.request.RegisterRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xiaolei03 on 16/12/2.
 */
@Mapper
public interface RegisterDao {

    @Select(" SELECT count(*) FROM user WHERE user_id = #{userId}")
    int checkUserIdExists(@Param("userId") String userId);

    @Select(" SELECT count(*) FROM user WHERE email = #{email}")
    int checkEmailExists(@Param("email") String email);

    @Insert(" INSERT INTO user(role, user_id, password, real_name, department, phone_number, email, active, student_type) " +
            " VALUES (#{registerRequest.role}, #{registerRequest.userId}, #{registerRequest.password}, " +
            " #{registerRequest.realName}, #{registerRequest.department}, #{registerRequest.phoneNumber}, " +
            " #{registerRequest.email}, #{registerRequest.active}, #{registerRequest.studentType}) ")
    int insertUserInfo(@Param("registerRequest") RegisterRequest registerRequest);

}