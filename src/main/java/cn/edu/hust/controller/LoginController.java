package cn.edu.hust.controller;

import cn.edu.hust.model.User;
import cn.edu.hust.model.request.ForgetPasswordRequest;
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
     * 登录主页
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/index.html";
    }

    /**
     * 点击登录
     *
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public CommonResponse login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            return loginService.login(loginRequest, session);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse().withCode(500).withMsg("系统繁忙");
        }

    }

    /**
     * 忘记密码
     *
     * @return
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        return "redirect:/index.html";
    }
}
