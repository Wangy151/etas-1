package cn.edu.hust.dao;

import cn.edu.hust.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by xiaolei03 on 16/12/4.
 */
@Mapper
public interface UserDao {

    @Select("SELECT user_id, password, department, real_name, " +
            " phone_number, email, role, active, update_time, login_time," +
            " remark FROM user WHERE user_id = #{username} OR email = #{username}")
    User getUserInfo(@Param("username") String username);

    @Update(" update user set password = #{newPassword} WHERE user_id = #{username} OR email = #{username} ")
    int updateUserPassword(@Param("newPassword") String newPassword,
                           @Param("username") String username);
}
