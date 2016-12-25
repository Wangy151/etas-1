package cn.edu.hust.controller;

import cn.edu.hust.model.request.ForgetPasswordRequest;
import cn.edu.hust.model.request.LoginRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by xiaolei03 on 16/12/1.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 首页
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
    @ResponseBody
    public CommonResponse forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest, HttpSession session) {
        try {
            return loginService.forgetPassword(forgetPasswordRequest, session);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse().withCode(500).withMsg("系统繁忙");
        }
    }
}
