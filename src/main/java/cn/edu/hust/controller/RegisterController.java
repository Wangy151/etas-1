package cn.edu.hust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaolei03 on 16/12/2.
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    /**
     * 检查用户名是否存在
     * @return
     */
    @RequestMapping(value = "/checkUsername")
    public String checkUsernameExists() {
        return "redirect:/index.html";
    }

    /**
     * 提交注册
     * @return
     */
    @RequestMapping(value = "/submit")
    public String registerSubmit() {
        return "redirect:/index.html";
    }
}
