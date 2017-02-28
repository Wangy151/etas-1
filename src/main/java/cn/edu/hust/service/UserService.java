package cn.edu.hust.service;

import cn.edu.hust.dao.UserDao;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.AdminActiveRequest;
import cn.edu.hust.model.request.AdminActiveTeacherSearchRequest;
import cn.edu.hust.model.request.UserProfileRequest;
import cn.edu.hust.model.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaolei03 on 16/12/4.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserInfo(String username) {
        return userDao.getUserInfo(username);
    }

    public boolean updateUserProfile(UserProfileRequest userProfileRequest) {
        return userDao.updateUserProfile(userProfileRequest) > 0;
    }

    public boolean updateUserPassword(String newPassword, String username) {
        return userDao.updateUserPassword(newPassword, username) > 0;
    }

    public String getStudentType(String userId){
        return userDao.getStudentType(userId);
    }

    public List<User> getBatchStudentType(List<String> userIdList) {
        return userDao.getBatchStudentType(userIdList);
    }

    public boolean insertUserInfo(UserProfileRequest userProfileRequest){
        return userDao.insertUserInfo(userProfileRequest) > 0;
    }

    public List<User> adminActiveTeacherSearch(AdminActiveTeacherSearchRequest searchRequest) {
        // activeStatus --> active
        String activeStatus = searchRequest.getActiveStatus();
        if ("已激活".equalsIgnoreCase(activeStatus)) {
            searchRequest.setActive(1);
        } else if ("未激活".equalsIgnoreCase(activeStatus)) {
            searchRequest.setActive(0);
        } else {
            searchRequest.setActive(null);
        }

        return userDao.adminActiveTeacherSearch(searchRequest);
    }

    public boolean updateActiveStatus(AdminActiveRequest activeRequest) {
        String[] userIds = activeRequest.getUserIds();
        if (null == userIds || userIds.length == 0) {
            return false;
        }

        return userDao.updateActiveStatus(activeRequest.getActive(), Arrays.asList(userIds)) > 0;
    }

}
