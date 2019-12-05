package com.android.yl.androidcodeproject.http;

/**
 * @author
 * @description 网络请求的配置
 * @time 2019/7/19
 */
public class HttpConfig {
    public String TAG = getClass().getSimpleName();
    public static String BASE_URL = "http://192.168.0.134:8080/****/";

    /**
     * 全局设置baseUrl
     *
     * @param baseUrl
     */
    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    //错误码
    public static final int ERROR_CODE_FAIL = 401;
    public static final int ERROR_CODE_NO_LOGIN = 402;
    public static final int ERROR_CODE_TOKEN_INVALID = 1011;//token失效
    public static final int ERROR_CODE_NODATA = 1012;//查不到数据
}
