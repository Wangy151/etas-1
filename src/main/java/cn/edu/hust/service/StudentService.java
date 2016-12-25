package cn.edu.hust.service;

import cn.edu.hust.dao.ThesisApplyDao;
import cn.edu.hust.model.request.DoctorThesisApplyInfoRequest;
import cn.edu.hust.model.request.MasterThesisApplyInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaolei03 on 16/12/6.
 */
@Service
public class StudentService {
    @Autowired
    private ThesisApplyDao thesisApplyDao;

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
