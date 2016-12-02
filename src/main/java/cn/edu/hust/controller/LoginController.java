package cn.edu.hust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String login(Map<String, Object> model) {
        model.put("userName", "ray");
        return "welcome";
    }

}
