package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt;

import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.MS_Result;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.MIF_Result;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.ML_Result;
import com.treasure_ct.network_xt.NetworkTask;
import com.treasure_ct.network_xt.UrlString;

/**
 * Created by treasure on 2016.09.04.
 */
public interface ML_Server {
    @UrlString("http://v3.wufazhuce.com:8000/api/movie/list/0")
    NetworkTask<ML_Result> getList();
    @UrlString("http://v3.wufazhuce.com:8000/api/movie/detail/%d")
    NetworkTask<MIF_Result> getData(long id);
    @UrlString("http://v3.wufazhuce.com:8000/api/movie/%d/story/1/0")
    NetworkTask<MS_Result> getStory(long id);
}
