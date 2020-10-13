package cn.edu.hust.dao;

import cn.edu.hust.model.StudentInfoImport;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xiaolei03 on 17/1/5.
 */
@Mapper
public interface StudentInfoImportDao {

    @Delete(" DELETE FROM student_info_import ")
    int deleteAllRecords();

    @InsertProvider(type=StudentInfoImportSql.class, method = "getInsertSql")
    int insertRecord(@Param("model")StudentInfoImport model);


    @Select(" SELECT yjxkmc FROM yjxk_map WHERE yjxkdm = #{yjxkdm}")
    String getYjxkmc(@Param("yjxkdm")String yjxkdm);

    @Select(" SELECT xh, name, csrq, yjxkdm, yjxkmc, ejxkdm, ejxkmc, ds, lwtm, rxnf, hxwsj, dbsj" +
            " FROM student_info_import WHERE xh = #{xh}")
    StudentInfoImport getStudentInfoImport(@Param("xh")String xh);


}
