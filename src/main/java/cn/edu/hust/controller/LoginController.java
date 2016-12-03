package cn.edu.hust.controller;

import cn.edu.hust.model.User;
import cn.edu.hust.model.request.LoginRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by xiaolei03 on 16/12/1.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/index.html";
    }

    /**
     * 点击登录
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public CommonResponse login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        CommonResponse commonResponse = new CommonResponse();

        String verifyCodeString = loginRequest.getVerifyCodeString();
        String verifyCodeStringFromSession = (String) session.getAttribute("verifyCodeString");

        if (StringUtils.isEmpty(verifyCodeString) ||
                StringUtils.isEmpty(verifyCodeStringFromSession) ||
                !verifyCodeString.equalsIgnoreCase(verifyCodeStringFromSession)) {
            return commonResponse.withCode(202).withMsg("验证码错误");
        }

        if (loginService.isLoginSuccess(loginRequest)) {
            // 登录成功
            User user = loginService.getUserInfo(loginRequest.getUsername());
            session.setAttribute("user", user);
            return commonResponse.withCode(200).withMsg("成功");
        }
        return commonResponse.withCode(201).withMsg("用户名或密码错误");
    }

}
