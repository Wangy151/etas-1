package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 16/12/25.
 */
public class StudentTypeRequest {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "StudentTypeRequest{" +
                "userId='" + userId + '\'' + '}';
    }
}
