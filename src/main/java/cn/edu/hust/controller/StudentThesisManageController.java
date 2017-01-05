package cn.edu.hust.controller;

import cn.edu.hust.common.ThesisApplyStatus;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.User;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by xiaolei03 on 17/1/4.
 */
@Controller
@RequestMapping(value = "/home/student/thesis/manage")
public class StudentThesisManageController {
    @Autowired
    private StudentService studentService;
    /**
     * 论文状态主页
     *
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

        if (!studentService.hasPermissionDeleteThesisApply(userId)) {
            return new CommonResponse().withCode(300).withMsg("学院教务员已审核通过,不能删除");
        }

        try {
            // TODO 测试
            studentService.deleteThesisApplyRecords(userId);
        } catch (Exception e) {
            // 异常情况
            e.printStackTrace();
            return new FailResponse();
        }

        return new SuccessResponse();
    }
}
