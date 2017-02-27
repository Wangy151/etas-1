package cn.edu.hust.controller;

import cn.edu.hust.model.User;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by jason on 2017/2/27.
 */
@Controller
@RequestMapping(value = "/home/admin/addAdministrator")
public class AdminAddAdministratorController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession session){
        return "admin_add_administrator";
    }

    @RequestMapping(value = "/save")
    public CommonResponse save(@RequestBody User user){
        return null;
    }

}
