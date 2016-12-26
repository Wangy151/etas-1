package cn.edu.hust.service;

import cn.edu.hust.dao.ThesisApplyDao;
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


    public boolean initMasterThesisApply(String userId) {

        return thesisApplyDao.initMasterThesisApply(userId) > 0;
    }

    public boolean initDocterThesisApply(String userId) {
        return thesisApplyDao.initDocterThesisApply(userId) > 0;
    }

    public boolean saveMaster(MasterThesisApplyInfoRequest masterThesisApplyInfoRequest) {
        return thesisApplyDao.saveMaster(masterThesisApplyInfoRequest) > 0;
    }

    public boolean saveDoctor(DoctorThesisApplyInfoRequest doctorThesisApplyInfoRequest) {
        return thesisApplyDao.saveDoctor(doctorThesisApplyInfoRequest) > 0;
    }
}
