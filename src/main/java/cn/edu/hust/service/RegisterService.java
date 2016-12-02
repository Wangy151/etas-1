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

    public boolean checkUsernameExists(String username) {
        return registerDao.checkUsernameExists(username) > 0;
    }
}
