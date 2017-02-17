package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 16/12/25.
 */
public class StudentTypeRequest {
    private String userId;
    private String studentType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    @Override
    public String toString() {
        return "StudentTypeRequest{" +
                "userId='" + userId + '\'' +
                ", studentType='" + studentType + '\'' +
                '}';
    }
}
