package com.android.yl.androidcodeproject.http;

import com.android.yl.androidcodeproject.utils.LogUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author
 * @description 网络请求的工具
 * @time 2019/7/18
 */
public class RetrofitUtils {
    public String TAG = getClass().getSimpleName();
    private static Retrofit retrofit;

    public static ApiService getService() {
        if (retrofit == null || !retrofit.baseUrl().toString().equals(HttpConfig.BASE_URL)) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.addInterceptor(new HttpInterceptor());
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpConfig.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build();
        }

        return retrofit.create(ApiService.class);
    }


    /**
     * 打印请求数据和返回数据
     */
    public static class HttpInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtils.i("\n");
            LogUtils.i("----------Request Start----------------");
            LogUtils.i("| " + request.toString());
            String method = request.method();
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    LogUtils.i("| RequestParams:{" + sb.toString() + "}");
                }
            }
            LogUtils.i("| Response:" + content);
            LogUtils.i("----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }


}
