package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 17/1/13.
 */
public class LoadBasicInfoTableRequest {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoadBasicInfoTableRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
