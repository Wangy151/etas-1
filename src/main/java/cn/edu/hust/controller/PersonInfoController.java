package cn.edu.hust.controller;

import cn.edu.hust.model.User;
import cn.edu.hust.model.request.UserProfileRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.UserService;
import cn.edu.hust.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jason on 2017/2/27.
 */
@Controller
@RequestMapping(value = "/home")
public class PersonInfoController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/student/personInfo/index")
    public String studentIndex(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        User user1 =  userService.getUserInfo(userId);
        model.addAttribute("user",user1);
        return "s_person_information";

    }

    @RequestMapping(value = "/student/personInfo/save")
    @ResponseBody
    public CommonResponse saveStudentProfile(@RequestBody UserProfileRequest userProfileRequest, HttpSession session){
        String userId = ((User) session.getAttribute("user")).getUserId();
        userProfileRequest.setUserId(userId);

        String mailVerifyCode = userProfileRequest.getMailVerifyCode();
        // 核对验证码
        String mailVerifyCodeFromSession = (String) session.getAttribute("mailVerifyCode");
        if (!StringUtils.isEmpty(mailVerifyCode) && !mailVerifyCode.equalsIgnoreCase(mailVerifyCodeFromSession)) {
            return new CommonResponse().withCode(300).withMsg("验证码错误");
        }

        // 更新个人资料
        if (userService.updateUserProfile(userProfileRequest)) {
            return new SuccessResponse();
        } else {
            return new FailResponse();
        }
    }

    @RequestMapping(value = "/student/personInfo/password/update")
    @ResponseBody
    public CommonResponse updateStudentPassword(@RequestBody UserProfileRequest userProfileRequest, HttpSession session) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        String newPasswordEncoded;
        try {
            newPasswordEncoded = MD5Util.encode(userProfileRequest.getNewPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new FailResponse();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new FailResponse();
        }

        if (userService.updateUserPassword(newPasswordEncoded, userId)) {
            return new SuccessResponse();
        } else {
            return new FailResponse();
        }

    }

    @RequestMapping(value = "/teacher/personInfo/index")
    public String teacherIndex(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        User user1 =  userService.getUserInfo(userId);
        model.addAttribute("user",user1);
        return "teacher_person_information";

    }

    @RequestMapping(value = "/teacher/personInfo/save")
    @ResponseBody
    public CommonResponse saveTeacherProfile(@RequestBody UserProfileRequest userProfileRequest, HttpSession session){
        String userId = ((User) session.getAttribute("user")).getUserId();
        userProfileRequest.setUserId(userId);

        String mailVerifyCode = userProfileRequest.getMailVerifyCode();
        // 核对验证码
        String mailVerifyCodeFromSession = (String) session.getAttribute("mailVerifyCode");
        if (!StringUtils.isEmpty(mailVerifyCode) && !mailVerifyCode.equalsIgnoreCase(mailVerifyCodeFromSession)) {
            return new CommonResponse().withCode(300).withMsg("验证码错误");
        }

        // 更新个人资料
        if (userService.updateUserProfile(userProfileRequest)) {
            return new SuccessResponse();
        } else {
            return new FailResponse();
        }
    }

    @RequestMapping(value = "/teacher/personInfo/password/update")
    @ResponseBody
    public CommonResponse updateTeacherPassword(@RequestBody UserProfileRequest userProfileRequest, HttpSession session) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        String newPasswordEncoded;
        try {
            newPasswordEncoded = MD5Util.encode(userProfileRequest.getNewPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new FailResponse();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new FailResponse();
        }

        if (userService.updateUserPassword(newPasswordEncoded, userId)) {
            return new SuccessResponse();
        } else {
            return new FailResponse();
        }

    }

    @RequestMapping(value = "/admin/personInfo/index")
    public String AdminIndex(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        User user1 =  userService.getUserInfo(userId);
        model.addAttribute("user",user1);
        return "admin_person_information";

    }

    @RequestMapping(value = "/admin/personInfo/save")
    @ResponseBody
    public CommonResponse saveAdminProfile(@RequestBody UserProfileRequest userProfileRequest, HttpSession session){
        String userId = ((User) session.getAttribute("user")).getUserId();
        userProfileRequest.setUserId(userId);

        String mailVerifyCode = userProfileRequest.getMailVerifyCode();
        // 核对验证码
        String mailVerifyCodeFromSession = (String) session.getAttribute("mailVerifyCode");
        if (!StringUtils.isEmpty(mailVerifyCode) && !mailVerifyCode.equalsIgnoreCase(mailVerifyCodeFromSession)) {
            return new CommonResponse().withCode(300).withMsg("验证码错误");
        }

        // 更新个人资料
        if (userService.updateUserProfile(userProfileRequest)) {
            return new SuccessResponse();
        } else {
            return new FailResponse();
        }
    }

    @RequestMapping(value = "/admin/personInfo/password/update")
    @ResponseBody
    public CommonResponse updateAdminPassword(@RequestBody UserProfileRequest userProfileRequest, HttpSession session) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        String newPasswordEncoded;
        try {
            newPasswordEncoded = MD5Util.encode(userProfileRequest.getNewPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new FailResponse();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new FailResponse();
        }

        if (userService.updateUserPassword(newPasswordEncoded, userId)) {
            return new SuccessResponse();
        } else {
            return new FailResponse();
        }

    }

}

