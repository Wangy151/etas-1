package cn.edu.hust.model.response;

/**
 * Created by xiaolei03 on 16/12/26.
 */
public class SuccessResponse extends CommonResponse {
    public SuccessResponse() {
        this.setCode(200);
        this.setMsg("成功");
    }
}
