package com.android.yl.androidcodeproject.http;

/**
 * @author YuanLin
 * @create 2019/7/30
 * @Describe
 */
public class BaseResponse {

    /**
     * msg : success
     * code : 0
     */

    private String msg;
    private int code;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
