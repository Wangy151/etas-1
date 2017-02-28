package cn.edu.hust.model.request;

import java.util.Date;

/**
 * Created by lxiao on 2017/2/28.
 */
public class UserProfileRequest {
    private String userId;
    private String password;
    private String newPassword;
    private String department;
    private String realName;
    private String phoneNumber;
    private String email;
    private String role;
    private String studentType;
    private int active;
    private Date updateTime;
    private Date loginTime;
    private String remark;
    private String mailVerifyCode;

    @Override
    public String toString() {
        return "UserProfileRequest{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", department='" + department + '\'' +
                ", realName='" + realName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", studentType='" + studentType + '\'' +
                ", active=" + active +
                ", updateTime=" + updateTime +
                ", loginTime=" + loginTime +
                ", remark='" + remark + '\'' +
                ", mailVerifyCode='" + mailVerifyCode + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMailVerifyCode() {
        return mailVerifyCode;
    }

    public void setMailVerifyCode(String mailVerifyCode) {
        this.mailVerifyCode = mailVerifyCode;
    }
}
