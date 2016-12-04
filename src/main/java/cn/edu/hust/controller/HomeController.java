package cn.edu.hust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by xiaolei03 on 16/12/3.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    /**
     * 个人中心主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String homeIndex() {
        return "home";
    }

}
