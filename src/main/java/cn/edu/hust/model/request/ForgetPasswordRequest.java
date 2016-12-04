package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 16/12/4.
 */
public class ForgetPasswordRequest {
    private String userId;
    private String verifyCodeString;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVerifyCodeString() {
        return verifyCodeString;
    }

    public void setVerifyCodeString(String verifyCodeString) {
        this.verifyCodeString = verifyCodeString;
    }
}
