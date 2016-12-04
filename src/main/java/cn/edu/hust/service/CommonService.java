package cn.edu.hust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * Created by xiaolei03 on 16/12/4.
 */
@Service
public class CommonService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送邮件
     * @param emailTo
     * @param subject
     * @param context
     * @param emailTemplatePath
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void sendEmail(String emailTo, String subject, Context context, String emailTemplatePath)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        String emailFrom = "华中大优秀论文申请系统 <hust_etas@163.com>";
        InternetAddress internetAddress = new InternetAddress(new String(emailFrom.getBytes("GBK"), "ISO-8859-1"));

        mimeMessageHelper.setFrom(internetAddress);
        mimeMessageHelper.setTo(emailTo);
        mimeMessageHelper.setSubject(subject);

        String content = templateEngine.process(emailTemplatePath, context);
        mimeMessageHelper.setText(content, true);

        mailSender.send(mimeMessage);
    }

}
