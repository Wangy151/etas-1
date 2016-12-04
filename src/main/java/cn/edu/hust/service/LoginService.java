package cn.edu.hust.service;

import cn.edu.hust.dao.LoginDao;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.ForgetPasswordRequest;
import cn.edu.hust.model.request.LoginRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.utils.MD5Util;
import cn.edu.hust.utils.RandomStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xiaolei03 on 16/12/1.
 */
@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private LoginDao loginDao;

    public CommonResponse login(LoginRequest loginRequest, HttpSession session) {
        CommonResponse commonResponse = new CommonResponse();
        // 1. 验证码
        String verifyCodeString = loginRequest.getVerifyCodeString();
        String verifyCodeStringFromSession = (String) session.getAttribute("verifyCodeString");

        if (StringUtils.isEmpty(verifyCodeString) ||
                StringUtils.isEmpty(verifyCodeStringFromSession) ||
                !verifyCodeString.equalsIgnoreCase(verifyCodeStringFromSession)) {
            return commonResponse.withCode(300).withMsg("验证码错误");
        }

        // 密码 md5加密
        String passwordEncode;
        try {
            passwordEncode = MD5Util.encode(loginRequest.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return commonResponse.withCode(500).withMsg("系统繁忙");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return commonResponse.withCode(500).withMsg("系统繁忙");
        }


        // 2. 验证用户名和密码
        if (loginDao.isLoginSuccess(loginRequest.getUsername(), passwordEncode) > 0) {
            // 登录成功
            // 3. 是否激活
            User user = userService.getUserInfo(loginRequest.getUsername());
            if (null == user) {
                return commonResponse.withCode(500).withMsg("系统繁忙");
            }
            if (1 == user.getActive()) {
                // 激活
                session.setAttribute("user", user);
                return commonResponse.withCode(200).withMsg("成功");
            } else {
                return commonResponse.withCode(302).withMsg("用户未激活，请联系管理员");
            }
        }

        return commonResponse.withCode(301).withMsg("用户名或密码错误");
    }

    @Transactional
    public CommonResponse forgetPassword(ForgetPasswordRequest forgetPasswordRequest, HttpSession session) throws UnsupportedEncodingException, MessagingException, NoSuchAlgorithmException {
        CommonResponse commonResponse = new CommonResponse();

        String verifyCodeStringFromSession = (String) session.getAttribute("verifyCodeString");
        String verifyCodeString = forgetPasswordRequest.getVerifyCodeString();

        if (StringUtils.isEmpty(verifyCodeString) ||
                StringUtils.isEmpty(verifyCodeStringFromSession) ||
                !verifyCodeString.equalsIgnoreCase(verifyCodeStringFromSession)) {
            return commonResponse.withCode(300).withMsg("验证码错误");
        }

        String userId = forgetPasswordRequest.getUserId();
        if (StringUtils.isEmpty(userId)) {
            return commonResponse.withCode(500).withMsg("系统繁忙");
        }

        User user = userService.getUserInfo(userId);
        if (null == user) {
            return commonResponse.withCode(301).withMsg("用户不存在");
        }


        String emailTo = user.getEmail();
        String subject = "您的密码已重置,请重新登录";
        String emailTemplatePath = "new_password_email_template";

        String newPassword = RandomStrUtil.getRandomString(8);
        Context context = new Context();
        context.setVariable("newPassword", newPassword);

        commonService.sendEmail(emailTo, subject, context, emailTemplatePath);
        String newPasswordEncode = MD5Util.encode(newPassword);
        userService.updateUserPassword(newPasswordEncode, userId);

        return commonResponse.withCode(200).withMsg("成功");
    }

}
