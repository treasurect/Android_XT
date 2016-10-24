package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.09.04.
 */
public class MIF_Result {
    @SerializedName("res")
    private int res;
    @SerializedName("data")
    private MIF_Entry data;

    public MIF_Entry getData() {
        return data;
    }

    public void setData(MIF_Entry data) {
        this.data = data;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
