package cn.edu.hust.controller;

import cn.edu.hust.model.User;
import cn.edu.hust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by jason on 2017/2/27.
 */
@Controller
@RequestMapping(value = "/home")
public class PersonInfoController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/student/personInfo/index")
    public String studentIndex(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        User user1 =  userService.getUserInfo(userId);
        model.addAttribute("user",user1);
        return "s_person_information";

    }

    @RequestMapping(value = "/teacher/personInfo/index")
    public String teacherIndex(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        User user1 =  userService.getUserInfo(userId);
        model.addAttribute("user",user1);
        return "teacher_person_information";

    }

    @RequestMapping(value = "/admin/personInfo/index")
    public String AdminIndex(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        User user1 =  userService.getUserInfo(userId);
        model.addAttribute("user",user1);
        return "admin_person_information";

    }

}

