package cn.edu.hust.model.request;

/**
 * Created by lxiao on 2017/2/28.
 */
public class AdminActiveTeacherSearchRequest {
    private String department;
    private String activeStatus;
    private Integer active;
    private String realName;
    private String userId;

    @Override
    public String toString() {
        return "AdminActiveTeacherSearchRequest{" +
                "department='" + department + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
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

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
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
