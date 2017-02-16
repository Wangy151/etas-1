package cn.edu.hust.controller;

import cn.edu.hust.common.ThesisApplyStatus;
import cn.edu.hust.model.DoctorThesisApply;
import cn.edu.hust.model.MasterThesisApply;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.LoadBasicInfoTableRequest;
import cn.edu.hust.model.request.StudentTypeRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    /**
     * 【优秀论文申请】状态主页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

        ThesisBasicInfo thesisBasicInfo = studentService.getThesisBasicInfo(userId);
        model.addAttribute("thesisBasicInfo", thesisBasicInfo);
        return "s_query_thesis_status";
    }

    /**
     *  申请页主页
     * @return
     */
    @RequestMapping(value = "/main")
    public String main() {
        return "s_thesis_apply_frame";
    }


    /**
     * 加载基本信息表
     */
    @RequestMapping(value = "/load/basicInfoTable")
    public String loadBasicInfoTablePage(@RequestBody LoadBasicInfoTableRequest loadBasicInfoTableRequest, Model model) {
        // 接收学号 和 pageType
        String userId = loadBasicInfoTableRequest.getUserId();
        String pageType = loadBasicInfoTableRequest.getPageType();

        if ("0".equalsIgnoreCase(pageType)) {
            // 0表示'新增申请'
            ThesisBasicInfo  thesisBasicInfo = studentService.getInitThesisBasicInfo(userId);
            model.addAttribute("thesisBasicInfo", thesisBasicInfo);
            return "s_apply_basic_info";
        } else if ("1".equalsIgnoreCase(pageType)) {
            // 1表示‘修改’
            ThesisBasicInfo thesisBasicInfo = studentService.getThesisBasicInfo(userId);
            model.addAttribute("thesisBasicInfo", thesisBasicInfo);
            return "s_apply_basic_info";
        } else {
            // 2表示‘查看’
            ThesisBasicInfo thesisBasicInfo = studentService.getThesisBasicInfo(userId);
            model.addAttribute("thesisBasicInfo", thesisBasicInfo);
            // 返回只读页面
            return "s_apply_basic_info_view";
        }
    }


    /**
     * 保存基本信息表
     *
     * @param thesisBasicInfo 前台参数
     * @return 200:保存成功 500:保存失败
     */
    @RequestMapping(value = "/save/basicInfoTable", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveThesisBasicInfoTable(@RequestBody ThesisBasicInfo thesisBasicInfo, HttpSession session) {
        // TODO 前端传studentType
        // 初始化
        studentService.initThesisBasicInfoTable(thesisBasicInfo.getZzxh(), session);

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
                                          @RequestParam("fileName") String fileName,
                                          @RequestParam("userId") String userId) {
        try {
            return studentService.uploadThesisPdf(file, fileName, userId);
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
    @RequestMapping(value = "/load/tjbFrame")
    public String loadTjbFrame() {
        return "s_thesis_apply_tjb_frame";
    }

    /**
     * 根据 [硕士]或[博士] load 不同推荐表
     *
     * @return
     */
    @RequestMapping(value = "/load/tjb", method = RequestMethod.POST)
    public String loadTjb(@RequestBody StudentTypeRequest studentTypeRequest, Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

        String studentType = studentTypeRequest.getStudentType();
        // pageType(0表示'新增申请',1表示‘编辑’,2表示‘查看’)
        String pageType = studentTypeRequest.getPageType();

        if ("master".equalsIgnoreCase(studentType)) {
            // 硕士

            if ("0".equalsIgnoreCase(pageType)) {
                // 新增申请
                MasterThesisApply masterThesisApply = new MasterThesisApply();
                masterThesisApply.setZzxh(userId);
                model.addAttribute("masterThesisApply", masterThesisApply);
                return "s_master_thesis_apply";
            } else if ("1".equalsIgnoreCase(pageType)) {
                // 编辑
                MasterThesisApply masterThesisApply = studentService.getMasterTjb(userId);
                model.addAttribute("masterThesisApply", masterThesisApply);
                return "s_master_thesis_apply";
            } else {
                // 查看
                MasterThesisApply masterThesisApply = studentService.getMasterTjb(userId);
                model.addAttribute("masterThesisApply", masterThesisApply);
                return "s_master_thesis_apply_view";
            }
        } else if ("doctor".equalsIgnoreCase(studentType)) {
            // 博士

            if ("0".equalsIgnoreCase(pageType)) {
                // 新增申请
                DoctorThesisApply doctorThesisApply = new DoctorThesisApply();
                doctorThesisApply.setZzxh(userId);
                model.addAttribute("doctorThesisApply", doctorThesisApply);
                return "s_doctor_thesis_apply";
            } else if ("1".equalsIgnoreCase(pageType)) {
                // 编辑
                DoctorThesisApply doctorThesisApply = studentService.getDoctorTjb(userId);
                model.addAttribute("doctorThesisApply", doctorThesisApply);
                return "s_doctor_thesis_apply";
            } else {
                // 查看
                DoctorThesisApply doctorThesisApply = studentService.getDoctorTjb(userId);
                model.addAttribute("doctorThesisApply", doctorThesisApply);
                return "s_doctor_thesis_apply_view";
            }
        } else {
            throw new Exception("error");
        }

    }


    /**
     * 保存申请表[硕士]
     *
     * @return
     */
    @RequestMapping(value = "/master/save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveMasterTjb(@RequestBody MasterThesisApply masterThesisApply, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        masterThesisApply.setZzxh(userId);

        CommonResponse commonResponse = new CommonResponse();

        // 初始化
        studentService.initMasterTjb(userId);

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
    @RequestMapping(value = "/doctor/save", method = RequestMethod.POST)
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

        if (studentService.hasThesisApply(userId)) {
            return new CommonResponse().withCode(300).withMsg("重复提交");
        }

        boolean isSuccess = studentService.updateThesisApplyStatus(ThesisApplyStatus.TO_REPORT, userId);
        if (isSuccess) {
            return new SuccessResponse();
        }
        return new FailResponse();
    }

}
