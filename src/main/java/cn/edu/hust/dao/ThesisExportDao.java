package cn.edu.hust.dao;

import cn.edu.hust.model.DoctorThesisApply;
import cn.edu.hust.model.MasterThesisApply;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import cn.edu.hust.service.AdminExportService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

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
}
