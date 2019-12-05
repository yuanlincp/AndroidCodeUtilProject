package com.android.yl.androidcodeproject.http;



import com.android.yl.androidcodeproject.utils.LogUtils;
import com.android.yl.androidcodeproject.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author YuanLin
 * @create 2019/7/30
 * @Describe
 */
public class BaseCallBack<T extends BaseResponse> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
//        LogUtils.i( "httpSuccess==" + new Gson().toJson(response.body()));
        if (response.isSuccessful() && response.body().getCode() == 0) {
            onSuccess(call, response);
        } else {
            onError(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        HttpLoadDialogUtils.hidDialog();
        LogUtils.i("httpFailure==" + t.toString());
    }

    /**
     * 请求成功，重写该方法
     *
     * @param call
     * @param response
     */
    protected void onSuccess(Call<T> call, Response<T> response) {
        HttpLoadDialogUtils.hidDialog();

    }

    /**
     * 请求失败
     *
     * @param response
     */
    protected void onError(Response<T> response) {
        HttpLoadDialogUtils.hidDialog();
        if (response != null && response.body() != null) {
            ToastUtils.toast(response.body().getMsg());
            disposeEorCode("", response.body().getCode());
        }
    }

    private final void disposeEorCode(String message, int code) {
        switch (code) {
            case HttpConfig.ERROR_CODE_FAIL:
            case HttpConfig.ERROR_CODE_NO_LOGIN:
            case HttpConfig.ERROR_CODE_TOKEN_INVALID://token失效
                // TODO: 2019-12-05 some things

                break;
        }
    }


}
