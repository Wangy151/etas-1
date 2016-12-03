package cn.edu.hust.controller;

import cn.edu.hust.utils.VerifyCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by xiaolei03 on 16/12/3.
 * 通用工具
 */
@Controller
@RequestMapping(value = "/")
public class CommonController {
    /**
     * 生成验证码
     *
     * @return
     */
    @RequestMapping(value = "/verifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 生成随机字串
        String verifyCodeString = VerifyCodeUtil.generateVerifyCode(4);
        // 存入会话session  删除以前的
        session.removeAttribute("verifyCodeString");
        session.setAttribute("verifyCodeString", verifyCodeString.toLowerCase());
        //生成图片
        int w = 100, h = 35;
        VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCodeString);
    }

}