package cn.edu.hust.model.request;

/**
 * Created by xiaolei03 on 17/1/5.
 */
public class AdminSearchRequest {
    private String department;
    private String applyYear;
    private String applyStatus;
    private String studentType;
    private String zzxm;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

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

    public String getZzxm() {
        return zzxm;
    }

    public void setZzxm(String zzxm) {
        this.zzxm = zzxm;
    }

    @Override
    public String toString() {
        return "TeacherSearchRequest{" +
                "department='" + department + '\'' +
                ", applyYear='" + applyYear + '\'' +
                ", applyStatus='" + applyStatus + '\'' +
                ", studentType='" + studentType + '\'' +
                ", zzxm='" + zzxm + '\'' +
                '}';
    }
}
