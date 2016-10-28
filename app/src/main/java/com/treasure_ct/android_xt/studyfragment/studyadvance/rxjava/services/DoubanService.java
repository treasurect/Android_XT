package com.treasure_ct.android_xt.studyfragment.studyadvance.rxjava.services;

/**
 * Created by treasure on 2016.10.27.
 */

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 豆瓣的 API 定义
 */
public interface DoubanService {
    @GET("movie/top250")
    Observable<String> getMovieTop250(@Query("start") int start, @Query("count") int count);
    @GET("/v2/movie/subject/id")
    Observable<String> getMovieDetail(@Query("id") int id);
}
