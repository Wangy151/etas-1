package cn.edu.hust.model;

/**
 * Created by lxiao on 2017/3/30.
 */
public class HustDepartment {
    private int id;
    private String departmentId;
    private String department;
    private String remarks;

    @Override
    public String toString() {
        return "HustDepartment{" +
                "id=" + id +
                ", departmentId='" + departmentId + '\'' +
                ", department='" + department + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
