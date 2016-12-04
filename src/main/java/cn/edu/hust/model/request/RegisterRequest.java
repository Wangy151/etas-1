package cn.edu.hust.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiaolei03 on 16/12/3.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {
    private String role;
    private String userId;
    private String password;
    private String repeatPassword;
    private String realName;
    private String department;
    private String phoneNumber;
    private String email;
    private String mailVerifyCode;
    private int active;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getMailVerifyCode() {
        return mailVerifyCode;
    }

    public void setMailVerifyCode(String mailVerifyCode) {
        this.mailVerifyCode = mailVerifyCode;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
