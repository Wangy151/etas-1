package cn.edu.hust.service;

import cn.edu.hust.common.ThesisApplyStatus;
import cn.edu.hust.dao.StudentInfoImportDao;
import cn.edu.hust.dao.ThesisApplyDao;
import cn.edu.hust.model.*;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.model.response.ThesisApplyAbstractResponse;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xiaolei03 on 16/12/6.
 */
@Service
public class StudentService {
    @Autowired
    private UserService userService;

    @Autowired
    private ThesisApplyDao thesisApplyDao;

    @Autowired
    private StudentInfoImportDao studentInfoImportDao;

    /**
     * 基本信息表
     */
    public ThesisApplyAbstractResponse getThesisApplyAbstrac(String userId) {
        ThesisApplyAbstractResponse thesisApplyAbstractResponse = new ThesisApplyAbstractResponse();

        ThesisBasicInfo thesisBasicInfo = thesisApplyDao.getThesisBasicInfo(userId);
        if (null == thesisBasicInfo) {
            thesisApplyAbstractResponse.setBasicTableFinish("未完成");
            thesisApplyAbstractResponse.setApplyStatus("待学生提交");
        } else {
            thesisApplyAbstractResponse.setBasicTableFinish("已完成");
            thesisApplyAbstractResponse.setApplyStatus(thesisBasicInfo.getApplyStatus());
        }

        if (this.isFinishMasterTjb(userId) || this.isFinishDoctorTjb(userId)) {
            thesisApplyAbstractResponse.setTjbFinish("已完成");
        } else {
            thesisApplyAbstractResponse.setTjbFinish("未完成");
        }

        return thesisApplyAbstractResponse;
    }



    public void initThesisBasicInfoTable(String userId) {
        if (thesisApplyDao.hasApplyBasicInfoTable(userId) <= 0) {
            thesisApplyDao.initThesisBasicInfoTable(userId);
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
     * 上传pdf论文
     *
     * @return
     */
    @Transactional
    public CommonResponse uploadThesisPdf(MultipartFile file, String fileName, String zzxh) throws IOException {
        if (null == file || StringUtils.isEmpty(fileName) || StringUtils.isEmpty(zzxh)) {
            return new FailResponse();
        }

        String fileSavePath = fileName;

        // delete old file
        File fileObj = new File(fileSavePath);
        if (fileObj.exists()) {
            fileObj.delete();
        }

        // save file
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileObj));
        out.write(file.getBytes());
        out.flush();
        out.close();

        // update column: upload_status
        thesisApplyDao.updateUploadStatus(1, zzxh);

        return new SuccessResponse();
    }

    public byte[] getFileByteArray(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            throw new FileNotFoundException(fileName);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
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


    public StudentInfoImport getStudentInfoImport(String userId) {
        return studentInfoImportDao.getStudentInfoImport(userId);
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

    public boolean isFinishMasterTjb(String userId) {
        MasterThesisApplyPartStatus masterThesisApplyPartStatus = thesisApplyDao.getMasterThesisApplyPartStatus(userId);
        if (null != masterThesisApplyPartStatus
                && 1 == masterThesisApplyPartStatus.getPart1()
                && 1 == masterThesisApplyPartStatus.getPart2()
                && 1 == masterThesisApplyPartStatus.getPart3()
                && 1 == masterThesisApplyPartStatus.getPart4()) {
            return true;
        }
        return false;
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

    public boolean isFinishDoctorTjb(String userId) {
        DoctorThesisApplyPartStatus doctorThesisApplyPartStatus = thesisApplyDao.getDoctorThesisApplyPartStatus(userId);
        if (null != doctorThesisApplyPartStatus
                && 1 == doctorThesisApplyPartStatus.getPart1()
                && 1 == doctorThesisApplyPartStatus.getPart2()) {
            return true;
        }
        return false;
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
