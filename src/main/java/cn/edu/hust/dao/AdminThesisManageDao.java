package cn.edu.hust.dao;

import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminSearchRequest;
import cn.edu.hust.service.AdminExportService;
import cn.edu.hust.service.AdminThesisManageService;
import cn.edu.hust.service.TeacherThesisManageService;
import org.apache.ibatis.annotations.*;

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

    @Update("<script>UPDATE master_thesis_apply SET review_date = #{reviewDate} WHERE zzxh in <foreach collection='userIdList' item='item' open='(' separator=',' close=')'>#{item}</foreach></script>")
    int updateMasterThesisApply(@Param("reviewDate") String reviewDate,
                                @Param("userIdList") List<String> userIdList);

    @Update("<script>UPDATE doctor_thesis_apply SET review_date = #{reviewDate} WHERE zzxh in <foreach collection='userIdList' item='item' open='(' separator=',' close=')'>#{item}</foreach></script>")
    int updateDoctorThesisApply(@Param("reviewDate") String reviewDate,
                                @Param("userIdList") List<String> userIdList);
}
