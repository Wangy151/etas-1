package cn.edu.hust.service;

import cn.edu.hust.dao.ThesisExportDao;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by xiaolei03 on 17/1/5.
 * 导出
 */
@Service
public class AdminExportService {
    @Autowired
    private ThesisExportDao exportDao;

    public List<ThesisBasicInfo> search(AdminExportSearchRequest adminExportSearchRequest) {
        return exportDao.getThesisBasicInfoList(adminExportSearchRequest);
    }

    public String getAdminExportSearchSql(AdminExportSearchRequest adminExportSearchRequest) {
        String querySql = " SELECT apply_year, department, student_type, zzxm, zzxh, lwtm, dsxm, apply_status FROM thesis_basic_info ";

        String applyYear = adminExportSearchRequest.getApplyYear();
        String applyStatus = adminExportSearchRequest.getApplyStatus();
        String studentType = adminExportSearchRequest.getStudentType();
        String department = adminExportSearchRequest.getDepartment();

        querySql += " AND apply_status = '" + applyStatus + "' ";

        if (!StringUtils.isEmpty(applyYear)) {
            querySql += " AND apply_year = '" + applyYear + "' ";
        }

        if (!StringUtils.isEmpty(studentType)) {
            querySql += " AND student_type = '" + studentType + "' ";
        }

        if (!StringUtils.isEmpty(department)) {
            querySql += " AND department = '" + department + "' ";
        }

        return querySql;
    }

}
