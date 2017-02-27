package cn.edu.hust.dao;

import cn.edu.hust.model.User;
import cn.edu.hust.model.response.CommonResponse;
import org.apache.ibatis.annotations.*;

/**
 * Created by xiaolei03 on 16/12/4.
 */
@Mapper
public interface UserDao {

    @Select("SELECT user_id, password, department, real_name, " +
            " phone_number, email, role, active, update_time, login_time, student_type ," +
            " remark FROM user WHERE user_id = #{username} OR email = #{username}")
    User getUserInfo(@Param("username") String username);

    @Update(" update user set password = #{newPassword} WHERE user_id = #{username} OR email = #{username} ")
    int updateUserPassword(@Param("newPassword") String newPassword,
                           @Param("username") String username);

    //created by jason
    @Select(" SELECT student_type FROM user WHERE user_id = #{userId} ")
    String getStudentType(@Param("userId") String userId);


    @Insert(" INSERT INTO user(role, user_id, password, real_name, department, phone_number, email, active, student_type) " +
            " VALUES (#{user.role}, #{user.userId}, #{user.password}, " +
            " #{user.realName}, #{user.department}, #{user.phoneNumber}, " +
            " #{user.email}, #{user.active}, #{user.studentType}) ")
    int insertUserInfo(@Param("user") User user);

    @Select(" SELECT count(*) FROM user WHERE user_id = #{userId}")
    int checkUserIdExists(@Param("userId") String userId);

}


