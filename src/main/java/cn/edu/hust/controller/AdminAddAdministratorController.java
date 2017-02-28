package cn.edu.hust.controller;

import cn.edu.hust.model.request.UserProfileRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by jason on 2017/2/27.
 */
@Controller
@RequestMapping(value = "/home/admin/addAdministrator")
public class AdminAddAdministratorController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession session){
        return "admin_add_administrator";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public CommonResponse save(@RequestBody UserProfileRequest userProfileRequest, HttpSession session){
        String mailVerifyCode = userProfileRequest.getMailVerifyCode();
        // 核对验证码
        String mailVerifyCodeFromSession = (String) session.getAttribute("mailVerifyCode");
        if (!StringUtils.isEmpty(mailVerifyCode) && !mailVerifyCode.equalsIgnoreCase(mailVerifyCodeFromSession)) {
            return new CommonResponse().withCode(300).withMsg("验证码错误");
        }

        if (userService.insertUserInfo(userProfileRequest)) {
            return new SuccessResponse();
        } else {
            return new FailResponse();
        }
    }

}
