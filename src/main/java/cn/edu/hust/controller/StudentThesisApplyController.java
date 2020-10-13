package cn.edu.hust.controller;

import cn.edu.hust.common.ThesisApplyStatus;
import cn.edu.hust.model.*;
import cn.edu.hust.model.request.LoadBasicInfoTableRequest;
import cn.edu.hust.model.request.StudentTypeRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.model.response.ThesisApplyAbstractResponse;
import cn.edu.hust.service.StudentService;
import cn.edu.hust.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by xiaolei03 on 16/12/6.
 */
@Controller
@RequestMapping(value = "/home/student/thesis/apply")
public class StudentThesisApplyController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    /**
     * 【优秀论文申请】主页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

        ThesisApplyAbstractResponse thesisApplyAbstractResponse = studentService.getThesisApplyAbstrac(userId);
        model.addAttribute("thesisApplyAbstractResponse", thesisApplyAbstractResponse);
        return "s_query_thesis_status";
    }

    /**
     * 基本信息表
     */
    @RequestMapping(value = "/basicInfoTable/create")
    public String createBasicInfoTable(@RequestBody LoadBasicInfoTableRequest loadBasicInfoTableRequest, Model model) {
        String userId = loadBasicInfoTableRequest.getUserId();

        ThesisBasicInfo  thesisBasicInfo = studentService.getInitThesisBasicInfo(userId);
        model.addAttribute("thesisBasicInfo", thesisBasicInfo);
        return "s_apply_basic_info_create";
    }

    @RequestMapping(value = "/basicInfoTable/edit")
    public String editBasicInfoTable(@RequestBody LoadBasicInfoTableRequest loadBasicInfoTableRequest, Model model) {
        String userId = loadBasicInfoTableRequest.getUserId();

        ThesisBasicInfo thesisBasicInfo = studentService.getThesisBasicInfo(userId);
        model.addAttribute("thesisBasicInfo", thesisBasicInfo);
        return "s_apply_basic_info_edit";
    }

    @RequestMapping(value = "/basicInfoTable/view")
    public String viewBasicInfoTable(@RequestBody LoadBasicInfoTableRequest loadBasicInfoTableRequest, Model model) {
        String userId = loadBasicInfoTableRequest.getUserId();

        ThesisBasicInfo thesisBasicInfo = studentService.getThesisBasicInfo(userId);
        model.addAttribute("thesisBasicInfo", thesisBasicInfo);
        // 返回只读页面
        return "s_apply_basic_info_view";
    }

    @RequestMapping(value = "/basicInfoTable/save")
    @ResponseBody
    public CommonResponse saveBasicInfoTable(@RequestBody ThesisBasicInfo thesisBasicInfo) {
        // 初始数据库
        studentService.initThesisBasicInfoTable(thesisBasicInfo.getZzxh());

        // 初始数据 applyYear applyStatus department;
        String applyYear = String.valueOf(DateTime.now().getYear());
        String applyStatus = ThesisApplyStatus.TO_SUBMIT.getValue();

        User user = userService.getUserInfo(thesisBasicInfo.getZzxh());
        String department = user.getDepartment();

        thesisBasicInfo.setApplyYear(applyYear);
        thesisBasicInfo.setApplyStatus(applyStatus);
        thesisBasicInfo.setDepartment(department);
        thesisBasicInfo.setUploadStatus(1);

        if (studentService.saveThesisBasicInfoTable(thesisBasicInfo)) {
            return new SuccessResponse();
        }
        return new FailResponse();
    }

    /**
     * 上传pdf论文
     *
     * @param file
     * @return 200:成功  300:论文中含有敏感字符(如华中科技大学字样) 500:失败
     */
    @RequestMapping(value = "/basicInfoTable/pdf/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateThesisPdf(@RequestParam("file") MultipartFile file,
                                          @RequestParam("fileName") String fileName) {
        try {
            return studentService.uploadThesisPdf(file, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new FailResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new FailResponse();
        }
    }

    /**
     * 下载pdf论文
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/basicInfoTable/pdf/download")
    public ResponseEntity<byte[]> downloadThesisPdf(@RequestParam("fileName") String fileName) {
        byte[] buf = null;
        try {
            buf = studentService.getFileByteArray(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(buf, httpHeaders, HttpStatus.CREATED);
    }


    /**
     * 加载优秀论文推荐表: 主页面
     */
    @RequestMapping(value = "/tjb/frame")
    public String loadTjbFrame() {
        return "s_thesis_apply_tjb_frame";
    }

    //created by jason
    @RequestMapping(value = "/tjb/create")
    public String createTjb(@RequestBody StudentTypeRequest studentTypeRequest, Model model) {
        String userId = studentTypeRequest.getUserId();
        String studentType = userService.getStudentType(userId);
        System.out.println("userId is " + userId + ";  studentType is " + studentType);

        if ("硕士".equalsIgnoreCase(studentType)) {
            StudentInfoImport studentInfoImport = studentService.getStudentInfoImport(userId);
            if (null == studentInfoImport) {
                // 找不到导入信息
                studentInfoImport = new StudentInfoImport();
                studentInfoImport.setXh(userId);
            }

            model.addAttribute("studentInfoImport", studentInfoImport);
            return "s_master_thesis_apply_create";
        } else {
            StudentInfoImport studentInfoImport = studentService.getStudentInfoImport(userId);
            if (null == studentInfoImport) {
                // 找不到导入信息
                studentInfoImport = new StudentInfoImport();
                studentInfoImport.setXh(userId);
            }
            model.addAttribute("studentInfoImport", studentInfoImport);
            return "s_doctor_thesis_apply_create";
        }
    }


    @RequestMapping(value = "/tjb/edit")
    public String editTjb(@RequestBody StudentTypeRequest studentTypeRequest, Model model) {
        String userId = studentTypeRequest.getUserId();
        String studentType = userService.getStudentType(userId);

        if ("硕士".equalsIgnoreCase(studentType)) {
            MasterThesisApply masterThesisApply = studentService.getMasterTjb(userId);
            // 防止空指针
            if (null == masterThesisApply) {
                masterThesisApply = new MasterThesisApply();
                masterThesisApply.setZzxh(userId);
            }
            model.addAttribute("masterThesisApply", masterThesisApply);
            return "s_master_thesis_apply_edit";
        } else {
            DoctorThesisApply doctorThesisApply = studentService.getDoctorTjb(userId);
            // 防止空指针
            if (null == doctorThesisApply) {
                doctorThesisApply = new DoctorThesisApply();
                doctorThesisApply.setZzxh(userId);
            }
            model.addAttribute("doctorThesisApply", doctorThesisApply);
            return "s_doctor_thesis_apply_edit";
        }
    }

    @RequestMapping(value = "/tjb/view")
    public String viewTjb(@RequestBody StudentTypeRequest studentTypeRequest, Model model) {
        String userId = studentTypeRequest.getUserId();
        String studentType = userService.getStudentType(userId);

        if ("硕士".equalsIgnoreCase(studentType)) {
            MasterThesisApply masterThesisApply = studentService.getMasterTjb(userId);
            // 防止空指针
            if (null == masterThesisApply) {
                masterThesisApply = new MasterThesisApply();
                masterThesisApply.setZzxh(userId);
            }
            model.addAttribute("masterThesisApply", masterThesisApply);
            return "s_master_thesis_apply_view";
        } else {
            DoctorThesisApply doctorThesisApply = studentService.getDoctorTjb(userId);
            // 防止空指针
            if (null == doctorThesisApply) {
                doctorThesisApply = new DoctorThesisApply();
                doctorThesisApply.setZzxh(userId);
            }
            model.addAttribute("doctorThesisApply", doctorThesisApply);
            return "s_doctor_thesis_apply_view";
        }

    }

    /**
     * 保存申请表[硕士]
     *
     * @return
     */
    @RequestMapping(value = "/tjb/master/save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveMasterTjb(@RequestBody MasterThesisApply masterThesisApply) {
        CommonResponse commonResponse = new CommonResponse();

        // 初始化
        studentService.initMasterTjb(masterThesisApply.getZzxh());

        boolean isSuccess = studentService.saveMasterTjb(masterThesisApply);
        if (isSuccess) {
            return commonResponse.withCode(200).withMsg("保存成功");
        }
        return commonResponse.withCode(500).withMsg("保存失败");
    }

    /**
     * 保存申请表[博士]
     *
     * @return
     */
    @RequestMapping(value = "/tjb/doctor/save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveDoctorTjb(@RequestBody DoctorThesisApply doctorThesisApply, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        doctorThesisApply.setZzxh(userId);

        CommonResponse commonResponse = new CommonResponse();

        // 初始化
        studentService.initDoctorThesisApply(userId);

        boolean isSuccess = studentService.saveDoctor(doctorThesisApply);
        if (isSuccess) {
            return commonResponse.withCode(200).withMsg("保存成功");
        }
        return commonResponse.withCode(500).withMsg("失败");
    }

    /**
     * 删除论文申请
     * @param session
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public CommonResponse deleteThesisApply(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

//        if (!studentService.hasPermissionDeleteThesisApply(userId)) {
//            return new CommonResponse().withCode(300).withMsg("学院教务员已审核通过,不能删除");
//        }

        try {
            studentService.deleteThesisApplyRecords(userId);
        } catch (Exception e) {
            // 异常情况
            e.printStackTrace();
            return new FailResponse();
        }

        return new SuccessResponse();
    }

    /**
     * 提交申请
     * @param session
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public CommonResponse submitThesisApply(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

//        if (studentService.hasThesisApply(userId)) {
//            return new CommonResponse().withCode(300).withMsg("重复提交");
//        }

        boolean isSuccess = studentService.updateThesisApplyStatus(ThesisApplyStatus.TO_REPORT, userId);
        if (isSuccess) {
            return new SuccessResponse();
        }
        return new FailResponse();
    }

}
