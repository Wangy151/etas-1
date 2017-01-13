package cn.edu.hust.service;

import cn.edu.hust.common.ThesisApplyStatus;
import cn.edu.hust.dao.StudentInfoImportDao;
import cn.edu.hust.dao.ThesisApplyDao;
import cn.edu.hust.model.DoctorThesisApply;
import cn.edu.hust.model.MasterThesisApply;
import cn.edu.hust.model.StudentInfoImport;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.DoctorThesisApplyInfoRequest;
import cn.edu.hust.model.request.MasterThesisApplyInfoRequest;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by xiaolei03 on 16/12/6.
 */
@Service
public class StudentService {
    @Autowired
    private ThesisApplyDao thesisApplyDao;

    @Autowired
    private StudentInfoImportDao studentInfoImportDao;

    /**
     * 基本信息表
     * @param userId
     * @return
     */
    public void initThesisBasicInfoTable(String userId, HttpSession session) {
        if (thesisApplyDao.hasApplyBasicInfoTable(userId) <= 0) {
            // 初始化数据 applyYear applyStatus department;
            String applyYear = String.valueOf(DateTime.now().getYear());
            String applyStatus = ThesisApplyStatus.TO_SUBMIT.getValue();

            User user = (User)session.getAttribute("user");
            String department = user.getDepartment();

            thesisApplyDao.initThesisBasicInfoTable(applyYear, applyStatus, department, userId);
        }
    }

    public ThesisBasicInfo getThesisBasicInfo(String userId) {
        return thesisApplyDao.getThesisBasicInfo(userId);
    }


    public ThesisBasicInfo getInitThesisBasicInfo(String xh) {
        StudentInfoImport studentInfoImport = studentInfoImportDao.getStudentInfoImport(xh);
        // 导入信息表中不存在该学号信息
        if (null == studentInfoImport) {
            // 只填充学号
            ThesisBasicInfo thesisBasicInfo = new ThesisBasicInfo();
            thesisBasicInfo.setZzxh(xh);
            return thesisBasicInfo;
        }

        ThesisBasicInfo thesisBasicInfo = new ThesisBasicInfo();
        thesisBasicInfo.setZzxh(studentInfoImport.getXh());
        thesisBasicInfo.setZzxm(studentInfoImport.getName());
        thesisBasicInfo.setCsny(studentInfoImport.getCsrq().substring(0, 6));
        thesisBasicInfo.setYjxkdm(studentInfoImport.getYjxkdm());
        thesisBasicInfo.setYjxkmc(studentInfoImport.getYjxkmc());
        thesisBasicInfo.setEjxkdm(studentInfoImport.getEjxkdm());
        thesisBasicInfo.setEjxkmc(studentInfoImport.getEjxkmc());
        thesisBasicInfo.setDsxm(studentInfoImport.getDs());
        thesisBasicInfo.setLwtm(studentInfoImport.getLwtm());
        thesisBasicInfo.setHdxwrq(studentInfoImport.getHxwsj());

        return thesisBasicInfo;
    }

    public boolean saveThesisBasicInfoTable(ThesisBasicInfo thesisBasicInfo) {
        // 初始化数据
        String filePathPrefix = thesisBasicInfo.getXxdm() + "_" + thesisBasicInfo.getEjxkdm()
                + "_" + thesisBasicInfo.getZzxh();

        String lwtjblj = filePathPrefix + "_ZPB"; // word论文推荐表路径
        String lwywlj = filePathPrefix + "_LW"; // pdf论文原文路径

        thesisBasicInfo.setLwtjblj(lwtjblj);
        thesisBasicInfo.setLwywlj(lwywlj);

        return thesisApplyDao.saveThesisBasicInfoTable(thesisBasicInfo) > 0;
    }

    /**
     * 是否已经提交过申请
     * @param userId
     * @return true: 已经提交过  false:未提交过
     */
    public boolean hasThesisApply(String userId) {
        String applyStatus = thesisApplyDao.queryThesisApplyStatus(userId);
        return !ThesisApplyStatus.TO_SUBMIT.getValue().equalsIgnoreCase(applyStatus);
    }

    public boolean updateThesisApplyStatus(ThesisApplyStatus applyStatus, String zzxh) {
        return thesisApplyDao.updateThesisApplyStatus(applyStatus.getValue(), zzxh) > 0;
    }

    /**
     * 是否可以删除申请
     * @param zzxh
     * @return  true: 可以删除  false:不能删除
     */
    public boolean hasPermissionDeleteThesisApply(String zzxh) {
        String applyStatus = thesisApplyDao.queryThesisApplyStatus(zzxh);
        if (ThesisApplyStatus.TO_SUBMIT.getValue().equalsIgnoreCase(applyStatus) ||
                ThesisApplyStatus.TO_REPORT.getValue().equalsIgnoreCase(applyStatus)) {
            // 待学生提交 or 待教务员审核 --> 可以删除
            return true;
        }

        return false;
    }


    /**
     * 硕士推荐表
     * @param userId
     */
    public void initMasterTjb(String userId) {
        if (thesisApplyDao.hasMasterUser(userId) <= 0) {
            thesisApplyDao.initMasterTjb(userId);
        }
    }

    public MasterThesisApply getMasterTjb(String userId) {
        return thesisApplyDao.getMasterTjb(userId);
    }

    public boolean saveMasterTjb(MasterThesisApply masterThesisApply) {
        String savePart = masterThesisApply.getPart();

        if ("part1".equalsIgnoreCase(savePart)) {
            return thesisApplyDao.saveMasterTjb1(masterThesisApply) > 0;
        } else if ("part2".equalsIgnoreCase(savePart)) {
            return thesisApplyDao.saveMasterTjb2(masterThesisApply) > 0;
        } else if ("part3".equalsIgnoreCase(savePart)) {
            return thesisApplyDao.saveMasterTjb3(masterThesisApply) > 0;
        } else if ("part4".equalsIgnoreCase(savePart)) {
            return thesisApplyDao.saveMasterTjb4(masterThesisApply) > 0;
        }

        return false;
    }

    /**
     * 博士推荐表
     * @param userId
     */
    public void initDoctorThesisApply(String userId) {
        if (thesisApplyDao.hasDoctorUser(userId) <= 0) {
            thesisApplyDao.initDoctorThesisApply(userId);
        }
    }


    public DoctorThesisApply getDoctorTjb(String userId) {
        return thesisApplyDao.getDoctorTjb(userId);
    }


    public boolean saveDoctor(DoctorThesisApply doctorThesisApply) {
        String savePart = doctorThesisApply.getPart();

        if ("part1".equalsIgnoreCase(savePart)) {
            return thesisApplyDao.saveDoctorTjb1(doctorThesisApply) > 0;
        } else if ("part2".equalsIgnoreCase(savePart)) {
            return thesisApplyDao.saveDoctorTjb2(doctorThesisApply) > 0;
        }

        return false;
    }

    @Transactional
    public void deleteThesisApplyRecords(String userId) {
        thesisApplyDao.deleteThesisBasicInfoRecord(userId);
        thesisApplyDao.deleteMasterTjbRecord(userId);
        thesisApplyDao.deleteDoctorTjbRecord(userId);
    }
}
