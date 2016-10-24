package com.treasure_ct.android_xt.studyfragment.corecontrols.listview.server;

import com.treasure_ct.android_xt.studyfragment.loadingways.asynctask.AsyncTask_NetWorkTask;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.model.Base_Result;
import com.treasure_ct.network_xt.UrlString;

/**
 * Created by treasure on 2016.09.03.
 */
public interface Base_Server {
    @UrlString("http://www.tngou.net/api/top/list?id=%d&page=%d&rows=%d")
    AsyncTask_NetWorkTask<Base_Result> getList(long id, int page, int rows);
}
