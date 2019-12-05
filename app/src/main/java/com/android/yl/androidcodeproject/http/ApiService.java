package com.android.yl.androidcodeproject.http;


import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author
 * @description 网络强求service
 * @time 2019/7/19
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("**/**")
    Call<BaseResponse> createChannel(@FieldMap Map<String, String> map);


    @GET("**/**")
    Call<BaseResponse> checkAppVersionInfo(@Query("appType") String appType);


    @FormUrlEncoded
    @POST("**/**")
    Call<BaseResponse> logout(@Field("token") String token);


    @Multipart
    @POST("**/**")
    Call<BaseResponse> uploadImage(@Part("token") String token, @Part MultipartBody.Part file);


}
