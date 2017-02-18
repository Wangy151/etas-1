package cn.edu.hust.dao;

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
    @SelectProvider(type = AdminExportService.class, method = "getAdminExportSearchSql")
    List<ThesisBasicInfo> getThesisBasicInfoList(AdminExportSearchRequest adminExportSearchRequest);
}
