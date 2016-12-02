package cn.edu.hust.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xiaolei03 on 16/12/2.
 */
@Mapper
public interface RegisterDao {

    @Select(" SELECT count(*) FROM user WHERE username = #{username}")
    int checkUsernameExists(@Param("username") String username);

}
