package cn.edu.hust.service;

import cn.edu.hust.dao.AdminThesisManageDao;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminReviewRequest;
import cn.edu.hust.model.request.AdminSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei03 on 17/1/5.
 */
@Service
public class AdminImportService {
    @Autowired
    private AdminThesisManageDao adminThesisManageDao;

    public List<ThesisBasicInfo> search(AdminSearchRequest adminSearchRequest) {
        return adminThesisManageDao.getThesisBasicInfoList(adminSearchRequest);
    }

    public String getSearchSql(AdminSearchRequest adminSearchRequest) {
        String querySql = " SELECT apply_year, zzxh, zzxm, student_type, dsxm, lwtm, apply_status FROM thesis_basic_info ";

        String department = adminSearchRequest.getDepartment();
        String applyYear = adminSearchRequest.getApplyYear();
        String applyStatus = adminSearchRequest.getApplyStatus();
        String studentType = adminSearchRequest.getStudentType();
        String zzxm = adminSearchRequest.getZzxm();

        querySql += " WHERE '1' = '1' ";

        if (!StringUtils.isEmpty(department)) {
            querySql += " AND department = '" + department + "' ";
        }

        if (!StringUtils.isEmpty(applyYear)) {
            querySql += " AND apply_year = '" + applyYear + "' ";
        }

        if (!StringUtils.isEmpty(applyStatus)) {
            // 勾选 申请状态
            querySql += " AND apply_status = '" + applyStatus + "' ";
        } else {
            // 注意: 若未勾选申请状态 --> [待学生提交]和[待学院上报]状态是不能查出来的
            querySql += " AND apply_status IN ('待学校审核', '通过审核') ";
        }

        if (!StringUtils.isEmpty(studentType)) {
            querySql += " AND student_type = '" + studentType + "' ";
        }

        if (!StringUtils.isEmpty(zzxm)) {
            querySql += " AND zzxm = '" + zzxm + "' ";
        }

        return querySql;
    }

    public boolean review(AdminReviewRequest adminReviewRequest) {
        String[] userIds = adminReviewRequest.getUserIds();
        if (null == userIds || userIds.length == 0) {
            return false;
        }

        return adminThesisManageDao.updateApplyStatus("通过审核", this.arrayToSql(userIds)) > 0;
    }

    public boolean cancelReview(AdminReviewRequest adminReviewRequest) {
        String[] userIds = adminReviewRequest.getUserIds();
        if (null == userIds || userIds.length == 0) {
            return false;
        }

        return adminThesisManageDao.updateApplyStatus("待学校审核", this.arrayToSql(userIds)) > 0;
    }

    private String arrayToSql(String[] arr) {
        String sql = "";
        for (int i = 0; i < arr.length; i++) {
            sql += "'" + arr[i] + "'";
            if (i < arr.length - 1) {
                sql += ", ";
            }
        }
        return sql;
    }

    /**
     * 生成sql
     * @param param
     * @return
     */
    public String getUpdateSql(Map<String, String> param) {
        String updateSql = "UPDATE thesis_basic_info SET apply_status = '";
        updateSql += param.get("applyStatus") + "' WHERE zzxh IN (";
        updateSql += param.get("userIdsForSql") + ")";

        return updateSql;
    }

}
