package cn.edu.hust.service;

import cn.edu.hust.dao.RegisterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
