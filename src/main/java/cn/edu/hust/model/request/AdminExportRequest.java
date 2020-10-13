package cn.edu.hust.model.request;

import java.util.Arrays;

/**
 * Created by lxiao on 2017/2/18.
 */
public class AdminExportRequest {
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
