package cn.edu.hust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaolei03 on 17/1/4.
 */
@Controller
@RequestMapping(value = "/home/student/thesis/manage")
public class StudentThesisManageController {
    /**
     * 论文状态主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "s_query_thesis_status";
    }
}
