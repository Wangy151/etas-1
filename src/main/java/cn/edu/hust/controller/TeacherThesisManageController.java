package cn.edu.hust.controller;

import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.TeacherReportRequest;
import cn.edu.hust.model.request.TeacherSearchRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.TeacherThesisManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiaolei03 on 17/1/5.
 * 上报学生优秀论文
 */
@Controller
@RequestMapping(value = "/home/teacher/thesis")
public class TeacherThesisManageController {
    @Autowired
    private TeacherThesisManageService thesisManageService;

    /**
     * 进入主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "teacher_report_thesis";
    }

    /**
     * 查询
     */
    @RequestMapping(value = "/search")
    public String search(@RequestBody TeacherSearchRequest teacherSearchRequest, Model model) {
        List<ThesisBasicInfo> thesisBasicInfoList = thesisManageService.search(teacherSearchRequest);
        model.addAttribute("thesisBasicInfoList", thesisBasicInfoList);
        return "teacher_report_thesis_divd_table";
    }

    /**
     * 上报
     * @param teacherReportRequest
     * @return
     */
    @RequestMapping(value = "/report")
    @ResponseBody
    public CommonResponse report(@RequestBody TeacherReportRequest teacherReportRequest) {
        if (thesisManageService.report(teacherReportRequest)) {
            return new SuccessResponse();
        }

        return new FailResponse();
    }

    /**
     * 取消上报
     * @param teacherReportRequest
     * @return
     */
    @RequestMapping(value = "/cancelReport")
    @ResponseBody
    public CommonResponse cancelReport(@RequestBody TeacherReportRequest teacherReportRequest) {
        if (thesisManageService.cancelReport(teacherReportRequest)) {
            return new SuccessResponse();
        }

        return new FailResponse();
    }

}
