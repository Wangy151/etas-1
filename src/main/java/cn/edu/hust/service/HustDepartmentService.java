package cn.edu.hust.service;

import cn.edu.hust.dao.HustDepartmentDao;
import cn.edu.hust.dao.UserDao;
import cn.edu.hust.model.HustDepartment;
import cn.edu.hust.model.User;
import cn.edu.hust.model.request.AdminActiveRequest;
import cn.edu.hust.model.request.AdminActiveTeacherSearchRequest;
import cn.edu.hust.model.request.UserProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaolei03 on 16/12/4.
 */
@Service
public class HustDepartmentService {
    @Autowired
    private HustDepartmentDao hustDepartmentDao;

    public List<HustDepartment> getAllDepartments() {
        return hustDepartmentDao.getAllDepartments();
    }

}
