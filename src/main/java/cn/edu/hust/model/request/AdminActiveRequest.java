package cn.edu.hust.model.request;

import java.util.Arrays;

/**
 * Created by xiaolei03 on 17/1/7.
 */
public class AdminActiveRequest {
    private String[] userIds;
    private Integer active;

    @Override
    public String toString() {
        return "AdminActiveRequest{" +
                "userIds=" + Arrays.toString(userIds) +
                ", active=" + active +
                '}';
    }

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
