package cn.edu.hust.dao;

import cn.edu.hust.model.DoctorThesisApply;
import cn.edu.hust.model.MasterThesisApply;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import cn.edu.hust.service.AdminExportService;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by lxiao on 2017/2/18.
 */
@Mapper
public interface ThesisExportDao {
    // 查询
    @SelectProvider(type = AdminExportService.class, method = "getAdminExportSearchSql")
    List<ThesisBasicInfo> getThesisBasicInfoList(AdminExportSearchRequest adminExportSearchRequest);

    // 导出excel
    @SelectProvider(type = AdminExportService.class, method = "getAdminExportExcelSql")
    List<ThesisBasicInfo> getExportExcel(String whereInSql);

    // 导出word
    @SelectProvider(type = AdminExportService.class, method = "getLwtjbSql")
    List<ThesisBasicInfo> getLwtjbljList(String whereInSql);

    @SelectProvider(type = AdminExportService.class, method = "getMasterTjbSql")
    List<MasterThesisApply> getMasterTjbList(String whereInSql);

    @SelectProvider(type = AdminExportService.class, method = "getDoctorTjbSql")
    List<DoctorThesisApply> getDoctorTjbList(String whereInSql);

    // 导出pdf
    @SelectProvider(type=AdminExportService.class, method = "getAdminExportPdfSql")
    String[] getExportPdf(String whereInSql);

    // 更新序号
    @Select(" SELECT zzxh, lwywlj, student_type, xxdm, ejxkdm FROM thesis_basic_info WHERE apply_year = #{applyYear} and apply_status = #{applyStatus}" +
            " ORDER BY department, student_type, zzxh ")
    List<ThesisBasicInfo> getUpdateXhStudentInfo(@Param("applyYear") String applyYear,
                                             @Param("applyStatus") String applyStatus);

    @Update(" UPDATE thesis_basic_info SET xh = #{xh}, lwtjblj = #{lwtjblj}, lwywlj = #{lwywlj} " +
            " WHERE zzxh = #{zzxh}")
    Integer updateStudentXh(@Param("xh") String xh,
                            @Param("lwtjblj") String lwtjblj,
                            @Param("lwywlj") String lwywlj,
                            @Param("zzxh") String zzxh);
}
