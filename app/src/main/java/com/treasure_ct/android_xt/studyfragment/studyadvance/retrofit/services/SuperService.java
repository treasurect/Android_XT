package com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.services;

import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.InfoModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by treasure on 2016.10.27.
 */

public interface SuperService {
    @GET("info")
    Call<String> getVersion();
    // info/abc/version/test
    @GET("info/abc/{item}/test")
    Call<String>getVersionItem(@Path("item")String item);

    /**
     * POST请求：参数以 key = value & key = value  方式提交
     * FormUrlEncoded 代表数据形式  key = values
     */
    @FormUrlEncoded
    @POST("info")
    Call<String>login(@Field("name")String name,@Field("pwd")String pwd);
    /**
     * PUT请求 传递一个数据实体类 使用JSON传递
     */
    Call<String>updateInfo(@Body InfoModel info);

    @POST("file")
    @Multipart
    Call<String>uploadFile(@Part("file")RequestBody requestBody);
}
