package com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.services;

import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.ApiListModel;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.ClassifyListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by treasure on 2016.10.27.
 */

public interface TngouService {
    //方法 -》 接口请求
    //Call实际上是网络请求的工具，可以同步异步 请求数据
    /**
     * 定义一个方法 对应某一个请求网址 并且声明数据返回的结构类型（通过ConvertFactory）
     */
    @GET("tnfs/api/classify")
    Call<ClassifyListModel> getClassifyList();
    /**
     * 定义网址   并制定 GET请求的查询参数   为 id == xxx
     */
    @GET("tnfs/api/list")
    Call<ApiListModel> getApiListById(@Query("id")String id);
}


