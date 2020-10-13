package cn.edu.hust.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiaolei03 on 16/12/3.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {
    private String username;
    private String password;
    private String verifyCodeString;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCodeString() {
        return verifyCodeString;
    }

    public void setVerifyCodeString(String verifyCodeString) {
        this.verifyCodeString = verifyCodeString;
    }
}
