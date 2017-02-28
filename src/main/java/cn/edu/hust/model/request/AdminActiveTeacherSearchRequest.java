package cn.edu.hust.model.request;

/**
 * Created by lxiao on 2017/2/28.
 */
public class AdminActiveTeacherSearchRequest {
    private String department;
    private int active;
    private String realName;
    private String userId;

    @Override
    public String toString() {
        return "AdminActiveTeacherSearchRequest{" +
                "department='" + department + '\'' +
                ", active=" + active +
                ", realName='" + realName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
