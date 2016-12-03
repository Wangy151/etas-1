package cn.edu.hust.service;

import cn.edu.hust.dao.LoginDao;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by xiaolei03 on 16/12/1.
 */
@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public boolean isLoginSuccess(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }

        return loginDao.isLoginSuccess(username, password) > 0;
    }

    public User getUserInfo(String username) {
        return loginDao.getUserInfo(username);
    }

}
