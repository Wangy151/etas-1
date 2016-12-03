package cn.edu.hust;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = cn.edu.hust.Application.class)
@WebAppConfiguration
public class SendMailTest {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        InternetAddress internetAddress = new InternetAddress(new String("华中大优秀论文申请系统 <hust_etas@163.com>".getBytes("GBK"),"ISO-8859-1"));
        mimeMessageHelper.setFrom(internetAddress);
        mimeMessageHelper.setTo("ray_here@qq.com");
//        mimeMessageHelper.setTo("1002328263@qq.com");
//        mimeMessageHelper.setTo("398830945@qq.com");

        mimeMessageHelper.setSubject("请验证您的邮箱地址");

        Context context = new Context();
        context.setVariable("mailVerifyCode", "xdsg12");
        String content = templateEngine.process("code_mail_page", context);

        mimeMessageHelper.setText(content, true);

        mailSender.send(mimeMessage);
    }
}
