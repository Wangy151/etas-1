package cn.edu.hust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaolei03 on 16/12/1.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {

    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/")
    public String home() {
        return "redirect:/index.html";
    }

    /**
     * 点击登录
     * @return
     */
    @RequestMapping(value = "/login/")
    @ResponseBody
    public String login() {
        return "login success";
    }

}
