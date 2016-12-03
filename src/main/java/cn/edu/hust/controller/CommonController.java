package cn.edu.hust.controller;

import cn.edu.hust.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaolei03 on 16/12/3.
 * 通用工具
 */
@Controller
@RequestMapping(value = "/")
public class CommonController {

    @Autowired
    private JavaMailSender mailSender;

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

    /**
     * 发送邮件
     *
     * @return
     */
//    @RequestMapping(value = "/sendMail")
//    public void sendMail(HttpSession session) throws IOException, MessagingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//        InternetAddress internetAddress = new InternetAddress(new String("华中大优秀论文申请系统 <hust_etas@163.com>".getBytes("GBK"),"ISO-8859-1"));
//        mimeMessageHelper.setFrom(internetAddress);
//        mimeMessageHelper.setTo("ray_here@qq.com");
////        mimeMessageHelper.setTo("1002328263@qq.com");
////        mimeMessageHelper.setTo("398830945@qq.com");
//
//        mimeMessageHelper.setSubject("请验证您的邮箱地址");
//
//        VelocityEngine velocityEngine = new VelocityEngine();
//
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("mailVerifyCode", "xdsg12");
//        String text = VelocityEngineUtils.mergeTemplateIntoString(
//                velocityEngine, "register_mail_verify_code.vm", "UTF-8", model);
//        mimeMessageHelper.setText(text, true);
//
//        mailSender.send(mimeMessage);
//    }

}