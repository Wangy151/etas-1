package cn.edu.hust.controller;

import cn.edu.hust.model.request.LoginRequest;
import cn.edu.hust.model.response.CommonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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
    public String index() {
        return "redirect:/index.html";
    }

    /**
     * 点击登录
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public CommonResponse login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        System.out.println(loginRequest.getVerifyCodeString());

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.withCode(201).withMsg("用户名或密码错误");
        return commonResponse;
    }

}
