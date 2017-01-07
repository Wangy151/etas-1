package cn.edu.hust.model.request;

import java.util.Arrays;

/**
 * Created by xiaolei03 on 17/1/7.
 */
public class TeacherReportRequest {
    private String[] userIds;

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "TeacherReportRequest{" +
                "userIds=" + Arrays.toString(userIds) +
                '}';
    }
}
