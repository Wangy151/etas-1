package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 17/2/18.
 */
public class AdminExportSearchRequest {
    private String applyYear;
    private String applyStatus;
    private String studentType;
    private String department;

    public String getApplyYear() {
        return applyYear;
    }

    public void setApplyYear(String applyYear) {
        this.applyYear = applyYear;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "AdminExportSearchRequest{" +
                "applyYear='" + applyYear + '\'' +
                ", applyStatus='" + applyStatus + '\'' +
                ", studentType='" + studentType + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
