package cn.edu.hust.controller;

import cn.edu.hust.model.HustDepartment;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminReviewRequest;
import cn.edu.hust.model.request.AdminSearchRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.AdminThesisManageService;
import cn.edu.hust.service.HustDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiaolei03 on 17/1/5.
 * 管理员审核学生优秀论文
 */
@Controller
@RequestMapping(value = "/home/admin/thesis")
public class AdminThesisManageController {
    @Autowired
    private AdminThesisManageService adminThesisManageService;

    @Autowired
    private HustDepartmentService hustDepartmentService;

    /**
     * 进入主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {

        List<HustDepartment> hustDepartmentList = hustDepartmentService.getAllDepartments();
        model.addAttribute("hustDepartmentList", hustDepartmentList);

        return "admin_review_thesis";
    }

    /**
     * 查询
     * @param adminSearchRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/search")
    public String search(@RequestBody AdminSearchRequest adminSearchRequest, Model model) {
        List<ThesisBasicInfo> thesisBasicInfoList = adminThesisManageService.search(adminSearchRequest);
        model.addAttribute("thesisBasicInfoList", thesisBasicInfoList);
        return "admin_review_thesis_divd_table";
    }

    /**
     * 通过审核
     * @param adminReviewRequest
     * @return
     */
    @RequestMapping(value = "/review")
    @ResponseBody
    public CommonResponse review(@RequestBody AdminReviewRequest adminReviewRequest) {
        if (adminThesisManageService.review(adminReviewRequest)) {
            return new SuccessResponse();
        }

        return new FailResponse();
    }

    /**
     * 取消通过审核
     * @param adminReviewRequest
     * @return
     */
    @RequestMapping(value = "/cancelReview")
    @ResponseBody
    public CommonResponse cancelReview(@RequestBody AdminReviewRequest adminReviewRequest) {
        if (adminThesisManageService.cancelReview(adminReviewRequest)) {
            return new SuccessResponse();
        }

        return new FailResponse();
    }

}
