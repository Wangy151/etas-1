package cn.edu.hust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaolei03 on 17/1/5.
 * 上报学生优秀论文
 */
@Controller
@RequestMapping(value = "/home/teacher/thesis")
public class TeacherThesisManageController {

    /**
     * 进入主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "teacher_report_thesis";
    }
}
