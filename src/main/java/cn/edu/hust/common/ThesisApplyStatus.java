package cn.edu.hust.common;

/**
 * Created by xiaolei03 on 17/1/4. and meihao
 */

/**
 * 申请状态： 待学生提交-->待学院上报-->待学校审核-->通过审核
 */
public enum ThesisApplyStatus {
    TO_SUBMIT("待学生提交"),
    TO_REPORT("待学院上报"),
    TO_REVIEW("待学校审核"),
    REVIEWED("通过审核");

    private String value;
    ThesisApplyStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
