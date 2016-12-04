package cn.edu.hust.dao;

import cn.edu.hust.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xiaolei03 on 16/12/3.
 */
@Mapper
public interface LoginDao {

    @Select("SELECT count(*) FROM user WHERE user_id = #{username} OR email = #{username} AND password = #{password}")
    int isLoginSuccess(@Param("username") String username, @Param("password") String password);

    @Select("SELECT user_id, password, department, real_name, " +
            " phone_number, email, role, active, update_time, login_time," +
            " remark FROM user WHERE user_id = #{username} OR email = #{username}")
    User getUserInfo(@Param("username") String username);

}