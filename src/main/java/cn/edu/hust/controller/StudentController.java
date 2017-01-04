package cn.edu.hust.controller;

import cn.edu.hust.model.MasterThesisApply;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.DoctorThesisApplyInfoRequest;
import cn.edu.hust.model.request.StudentTypeRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String loadBasicInfoTablePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

        // 判断是否已初始化
        if (!studentService.hasApplyBasicInfoTable(userId)) {
            studentService.initThesisBasicInfoTable(userId);
        } else {
            ThesisBasicInfo thesisBasicInfo = studentService.getThesisBasicInfo(userId);
            model.addAttribute("thesisBasicInfo", thesisBasicInfo);
        }
        return "s_apply_basic_info";
    }


    /**
     * 保存基本信息表
     * @param thesisBasicInfo 前台参数
     * @return 200:保存成功 500:保存失败
     */
    @RequestMapping(value = "/apply/save/basicInfoTable", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveThesisBasicInfoTable(@RequestBody ThesisBasicInfo thesisBasicInfo) {
        System.out.println(thesisBasicInfo.toString());

        if (studentService.saveThesisBasicInfoTable(thesisBasicInfo)) {
            return new SuccessResponse();
        }
        return new FailResponse();
    }


    /**
     * 加载优秀论文推荐表: 主页面
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
    public String loadTjb(@RequestBody StudentTypeRequest studentTypeRequest, Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

        String studentType = studentTypeRequest.getStudentType();
        if ("master".equalsIgnoreCase(studentType)) {
            // 初始化
            studentService.initMasterTjb(userId);

            MasterThesisApply masterThesisApply = studentService.getMasterTjb(userId);
            model.addAttribute("masterThesisApply", masterThesisApply);
            return "s_master_thesis_apply";
        } else if ("doctor".equalsIgnoreCase(studentType)) {
            // 初始化
            studentService.initDoctorThesisApply(userId);


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
    @ResponseBody
    public CommonResponse saveMasterTjb(@RequestBody MasterThesisApply masterThesisApply, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        masterThesisApply.setZzxh(userId);

        CommonResponse commonResponse = new CommonResponse();

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
    @RequestMapping(value = "/apply/doctor/save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveDoctorTjb(@RequestBody DoctorThesisApplyInfoRequest doctorThesisApplyInfoRequest) {
        CommonResponse commonResponse = new CommonResponse();

        boolean isSuccess = studentService.saveDoctor(doctorThesisApplyInfoRequest);
        if (isSuccess) {
            return commonResponse.withCode(200).withMsg("保存成功");
        }
        return commonResponse.withCode(500).withMsg("失败");
    }

}
