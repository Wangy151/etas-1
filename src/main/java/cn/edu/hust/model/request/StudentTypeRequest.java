package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 16/12/25.
 */
public class StudentTypeRequest {
    private String studentType;
    private String pageType;

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    @Override
    public String toString() {
        return "StudentTypeRequest{" +
                "studentType='" + studentType + '\'' +
                ", pageType='" + pageType + '\'' +
                '}';
    }
}
