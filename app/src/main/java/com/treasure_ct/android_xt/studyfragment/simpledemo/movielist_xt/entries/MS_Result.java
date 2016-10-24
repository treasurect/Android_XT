package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.09.04.
 */
public class MS_Result {
    @SerializedName("res")
    private int res;
    @SerializedName("data")
    private MS_Data msData;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public MS_Data getMsData() {
        return msData;
    }

    public void setMsData(MS_Data msData) {
        this.msData = msData;
    }
}
