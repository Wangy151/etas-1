package cn.edu.hust.service;

import cn.edu.hust.dao.RegisterDao;
import cn.edu.hust.model.request.RegisterRequest;
import cn.edu.hust.model.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by xiaolei03 on 16/12/2.
 */
@Service
public class RegisterService {
    @Autowired
    private RegisterDao registerDao;

    public boolean checkUsernameExists(String userId) {
        return registerDao.checkUserIdExists(userId.trim()) > 0;
    }

    public boolean checkEmailExists(String email) {
        return registerDao.checkEmailExists(email.trim()) > 0;
    }

    public CommonResponse registerSubmit(RegisterRequest registerRequest, String mailVerifyCodeFromSession) {
        CommonResponse commonResponse = new CommonResponse();

        String mailVerifyCode = registerRequest.getMailVerifyCode();
        if (StringUtils.isEmpty(mailVerifyCodeFromSession) ||
                StringUtils.isEmpty(mailVerifyCode) ||
                !mailVerifyCodeFromSession.equalsIgnoreCase(mailVerifyCode)) {
            return commonResponse.withCode(300).withMsg("邮箱验证码错误");
        }

        if ("学院教务员".equalsIgnoreCase(registerRequest.getRole())) {
            registerRequest.setActive(0); // 未激活
        } else {
            // 学生
            registerRequest.setActive(1); // 激活
        }

        if (registerDao.insertUserInfo(registerRequest) > 0) {
            if ("学生".equalsIgnoreCase(registerRequest.getRole())) {
                return commonResponse.withCode(200).withMsg("学生注册成功");
            } else {
                return commonResponse.withCode(201).withMsg("学院教务员注册成功");
            }
        }
        return commonResponse.withCode(500).withMsg("失败");
    }
}
