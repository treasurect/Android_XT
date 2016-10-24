package com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt;
import com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.entries.Netease_Result;
import com.treasure_ct.network_xt.NetworkTask;
import com.treasure_ct.network_xt.UrlString;

public interface NeteaseServer {
    @UrlString("http://c.m.163.com/nc/article/headline/T1348647853363/%d-%d.html")
    NetworkTask<Netease_Result> getHeadline(int page, int rows);
}
