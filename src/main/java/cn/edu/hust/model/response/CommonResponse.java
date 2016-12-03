package cn.edu.hust.model.response;

/**
 * Created by xiaolei03 on 16/12/2.
 */
public class CommonResponse {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CommonResponse withCode(int code) {
        this.code = code;
        return this;
    }

    public CommonResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
