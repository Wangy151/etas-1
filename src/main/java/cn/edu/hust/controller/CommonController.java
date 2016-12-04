package cn.edu.hust.controller;

import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.utils.RandomStrUtil;
import cn.edu.hust.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 生成图片验证码
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
     * 发送邮件: 验证码
     *
     * @return
     */
    @RequestMapping(value = "/sendVerifyCodeMail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse sendMailForVerifyCode(@RequestParam String emailTo, HttpSession session) throws IOException, MessagingException {
        CommonResponse commonResponse = new CommonResponse();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            InternetAddress internetAddress = new InternetAddress(new String("华中大优秀论文申请系统 <hust_etas@163.com>".getBytes("GBK"), "ISO-8859-1"));

            mimeMessageHelper.setFrom(internetAddress);
            mimeMessageHelper.setTo(emailTo);

            mimeMessageHelper.setSubject("请验证您的邮箱地址");


            String mailVerifyCode = RandomStrUtil.getRandomString(6);
            session.removeAttribute("mailVerifyCode");
            session.setAttribute("mailVerifyCode", mailVerifyCode);

            Context context = new Context();
            context.setVariable("mailVerifyCode", mailVerifyCode);
            String content = templateEngine.process("code_mail_page", context);

            mimeMessageHelper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            return commonResponse.withCode(500).withMsg("失败");
        }

        return commonResponse.withCode(200).withMsg("成功");
    }

}