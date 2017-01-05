package cn.edu.hust.dao;

import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.TeacherSearchRequest;
import cn.edu.hust.service.TeacherThesisManageService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by xiaolei03 on 17/1/5.
 */
@Mapper
public interface TeacherThesisManageDao {

    @SelectProvider(type = TeacherThesisManageService.class, method = "getTeacherSearchSql")
    List<ThesisBasicInfo> getThesisBasicInfoList(TeacherSearchRequest teacherSearchRequest);
}
