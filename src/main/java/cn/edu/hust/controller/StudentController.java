package cn.edu.hust.controller;

import cn.edu.hust.model.request.DoctorThesisApplyInfoRequest;
import cn.edu.hust.model.request.MasterThesisApplyInfoRequest;
import cn.edu.hust.model.request.StudentTypeRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by xiaolei03 on 16/12/6.
 */
@Controller
@RequestMapping(value = "/home/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 进入申请页面
     *
     * @return
     */
    @RequestMapping(value = "/apply/index")
    public String index() {
        return "s_thesis_apply_frame";
    }


    /**
     * 加载基本信息表
     */
    @RequestMapping(value = "/apply/load/basicInfoTable")
    public String loadBasicInfoTablePage() {
        return "s_apply_basic_info";
    }

    /**
     * 加载优秀论文推荐表: 页面
     */
    @RequestMapping(value = "/apply/load/tjbFrame")
    public String loadTjbFrame() {
        return "s_thesis_apply_tjb_frame";
    }

    /**
     * 根据 [硕士]或[博士] load 不同推荐表
     *
     * @return
     */
    @RequestMapping(value = "/apply/load/tjb", method = RequestMethod.POST)
    public String loadTjb(@RequestBody StudentTypeRequest StudentTypeRequest, HttpSession session) throws Exception {
        String userId = (String) session.getAttribute("user");
        String studentType = StudentTypeRequest.getStudentType();
        if ("master".equalsIgnoreCase(studentType)) {
//            studentService.initMasterThesisApply(userId);
            return "s_master_thesis_apply";
        } else if ("doctor".equalsIgnoreCase(studentType)) {
//            studentService.initDocterThesisApply(userId);
            return "s_doctor_thesis_apply";
        } else {
            throw new Exception("error");
        }

    }

    /**
     * 保存申请表[硕士]
     *
     * @return
     */
    @RequestMapping(value = "/apply/master/save", method = RequestMethod.POST)
    public CommonResponse saveMaster(@RequestBody MasterThesisApplyInfoRequest masterThesisApplyInfoRequest) {
        CommonResponse commonResponse = new CommonResponse();
        boolean isSuccess = studentService.saveMaster(masterThesisApplyInfoRequest);
        if (isSuccess) {
            return commonResponse.withCode(200).withMsg("保存成功");
        }
        return commonResponse.withCode(500).withMsg("失败");
    }

    /**
     * 保存申请表[博士]
     *
     * @return
     */
    @RequestMapping(value = "/apply/doctor/save", method = RequestMethod.POST)
    public CommonResponse saveDoctor(@RequestBody DoctorThesisApplyInfoRequest doctorThesisApplyInfoRequest) {
        CommonResponse commonResponse = new CommonResponse();

        boolean isSuccess = studentService.saveDoctor(doctorThesisApplyInfoRequest);
        if (isSuccess) {
            return commonResponse.withCode(200).withMsg("保存成功");
        }
        return commonResponse.withCode(500).withMsg("失败");
    }

    /**
     * 提交申请表
     *
     * @return
     */
    @RequestMapping(value = "/apply/submit", method = RequestMethod.POST)
    public String submit() {
        return "";
    }

}
