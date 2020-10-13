package cn.edu.hust.model.response;

/**
 * Created by lxiao on 2017/2/17.
 */
public class ThesisApplyAbstractResponse {
    private String basicTableFinish;
    private String tjbFinish;
    private String applyStatus;

    public String getBasicTableFinish() {
        return basicTableFinish;
    }

    public void setBasicTableFinish(String basicTableFinish) {
        this.basicTableFinish = basicTableFinish;
    }

    public String getTjbFinish() {
        return tjbFinish;
    }

    public void setTjbFinish(String tjbFinish) {
        this.tjbFinish = tjbFinish;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Override
    public String toString() {
        return "ThesisApplyAbstractResponse{" +
                "basicTableFinish='" + basicTableFinish + '\'' +
                ", tjbFinish='" + tjbFinish + '\'' +
                ", applyStatus='" + applyStatus + '\'' +
                '}';
    }
}
