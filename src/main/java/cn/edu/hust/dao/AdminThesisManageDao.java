package cn.edu.hust.dao;

import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminSearchRequest;
import cn.edu.hust.service.AdminThesisManageService;
import cn.edu.hust.service.TeacherThesisManageService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * Created by xiaolei03 on 17/1/5.
 */
@Mapper
public interface AdminThesisManageDao {

    @SelectProvider(type = AdminThesisManageService.class, method = "getSearchSql")
    List<ThesisBasicInfo> getThesisBasicInfoList(AdminSearchRequest adminSearchRequest);

    @UpdateProvider(type = AdminThesisManageService.class, method = "getUpdateSql")
    int updateApplyStatus(@Param("applyStatus") String applyStatus,
                          @Param("whereInSql") String whereInSql);

}
