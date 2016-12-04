package cn.edu.hust.dao;

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

}
