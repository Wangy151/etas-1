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
            return commonResponse.withCode(201).withMsg("邮箱验证码错误");
        }

        return commonResponse.withCode(200).withMsg("成功");


    }
}
