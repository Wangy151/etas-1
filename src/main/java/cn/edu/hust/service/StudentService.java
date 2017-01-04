package cn.edu.hust.service;

import cn.edu.hust.dao.ThesisApplyDao;
import cn.edu.hust.model.DoctorThesisApply;
import cn.edu.hust.model.MasterThesisApply;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.DoctorThesisApplyInfoRequest;
import cn.edu.hust.model.request.MasterThesisApplyInfoRequest;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaolei03 on 16/12/6.
 */
@Service
public class StudentService {
    @Autowired
    private ThesisApplyDao thesisApplyDao;

    /**
     * 基本信息表
     * @param userId
     * @return
     */
    public boolean hasApplyBasicInfoTable(String userId) {
        return thesisApplyDao.hasApplyBasicInfoTable(userId) > 0;
    }

    public boolean initThesisBasicInfoTable(String userId) {
        return thesisApplyDao.initThesisBasicInfoTable(userId) > 0;
    }

    public ThesisBasicInfo getThesisBasicInfo(String userId) {
        return thesisApplyDao.getThesisBasicInfo(userId);
    }

    public boolean saveThesisBasicInfoTable(ThesisBasicInfo thesisBasicInfo) {
        thesisBasicInfo.setApplyStatus("待提交");
        int year = DateTime.now().getYear();
        thesisBasicInfo.setApplyYear(String.valueOf(year));

        return thesisApplyDao.saveThesisBasicInfoTable(thesisBasicInfo) > 0;
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
}
