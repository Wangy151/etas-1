package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 17/1/13.
 */
public class LoadBasicInfoTableRequest {
    private String userId;
    private String pageType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    @Override
    public String toString() {
        return "LoadBasicInfoTableRequest{" +
                "userId='" + userId + '\'' +
                ", pageType='" + pageType + '\'' +
                '}';
    }
}
