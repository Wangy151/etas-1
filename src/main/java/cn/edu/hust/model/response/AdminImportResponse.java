package cn.edu.hust.model.response;

/**
 * Created by xiaolei03 on 17/1/12.
 */
public class AdminImportResponse extends CommonResponse {
    private int totalNum;
    private int errorRowNum;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public AdminImportResponse withTotalNum(int totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public int getErrorRowNum() {
        return errorRowNum;
    }

    public void setErrorRowNum(int errorRowNum) {
        this.errorRowNum = errorRowNum;
    }

    public AdminImportResponse withErrorRowNum(int errorRowNum) {
        this.errorRowNum = errorRowNum;
        return this;

    }

    @Override
    public String toString() {
        return "AdminImportResponse{" +
                "totalNum=" + totalNum +
                ", errorRowNum=" + errorRowNum +
                '}';
    }
}
