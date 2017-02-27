package cn.edu.hust.service;

import cn.edu.hust.dao.AdminThesisManageDao;
import cn.edu.hust.dao.TeacherThesisManageDao;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.AdminReviewRequest;
import cn.edu.hust.model.request.AdminSearchRequest;
import cn.edu.hust.model.request.TeacherReportRequest;
import cn.edu.hust.model.request.TeacherSearchRequest;
import cn.edu.hust.utils.DatetimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei03 on 17/1/5.
 */
@Service
public class AdminThesisManageService {
    @Autowired
    private AdminThesisManageDao adminThesisManageDao;

    @Autowired
    private UserService userService;

    public List<ThesisBasicInfo> search(AdminSearchRequest adminSearchRequest) {
        return adminThesisManageDao.getThesisBasicInfoList(adminSearchRequest);
    }

    public String getSearchSql(AdminSearchRequest adminSearchRequest) {
        String querySql = " SELECT apply_year, zzxh, zzxm, student_type, dsxm, lwtm, apply_status, department FROM thesis_basic_info ";

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

    @Transactional
    public boolean review(AdminReviewRequest adminReviewRequest) {
        String[] userIds = adminReviewRequest.getUserIds();
        if (null == userIds || userIds.length == 0) {
            return false;
        }

        boolean updateApplyStatus = adminThesisManageDao.updateApplyStatus("通过审核", this.arrayToSql(userIds)) > 0;

        List<User> userList = userService.getBatchStudentType( Arrays.asList(userIds));

        List<String> masterUserIdList = new ArrayList<String>();
        List<String> doctorUserIdList = new ArrayList<String>();
        for (User user : userList) {
            if ("硕士".equalsIgnoreCase(user.getStudentType())) {
                masterUserIdList.add(user.getUserId());
            } else if ("博士".equalsIgnoreCase(user.getStudentType())) {
                doctorUserIdList.add(user.getUserId());
            }
        }

        String reviewDate = DatetimeUtil.getYYYYMMDD();

        boolean updateMasterThesisApply = true;
        if (masterUserIdList.size() > 0) {
            updateMasterThesisApply = adminThesisManageDao.updateMasterThesisApply(reviewDate, masterUserIdList) > 0;

        }
        boolean updateDoctorThesisApply = true;
        if (doctorUserIdList.size() > 0) {
            updateDoctorThesisApply = adminThesisManageDao.updateDoctorThesisApply(reviewDate, doctorUserIdList) > 0;
        }

        return updateApplyStatus && updateMasterThesisApply && updateDoctorThesisApply;
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
        updateSql += param.get("whereInSql") + ")";

        return updateSql;
    }

//    public String getUpdate

}
