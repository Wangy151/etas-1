package cn.edu.hust.controller;

import cn.edu.hust.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaolei03 on 16/12/2.
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    /**
     * 检查学号或教工号是否可以注册
     * @return true: 可以注册; false: 已存在,不能注册
     */
    @RequestMapping(value = "/checkUserIdRegister", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUserIdRegister(@RequestParam String userId) {
        return !registerService.checkUsernameExists(userId);
    }

    /**
     * 检查email是否可以注册
     * @return true: 用户可以注册; false: 用户不能注册
     */
    @RequestMapping(value = "/checkEmailRegister", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkEmailRegister(@RequestParam String email) {
        return !registerService.checkEmailExists(email);
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
