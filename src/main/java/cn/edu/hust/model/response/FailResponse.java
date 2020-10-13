package cn.edu.hust.model.response;

/**
 * Created by xiaolei03 on 16/12/26.
 */
public class FailResponse extends CommonResponse {
    public FailResponse() {
        this.setCode(500);
        this.setMsg("失败");
    }
}
