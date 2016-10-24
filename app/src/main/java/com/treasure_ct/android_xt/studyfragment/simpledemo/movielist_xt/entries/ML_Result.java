package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by treasure on 2016.09.04.
 */
public class ML_Result {
    @SerializedName("res")
    private int res;
    @SerializedName("data")
    private List<ML_Entry>list;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<ML_Entry> getList() {
        return list;
    }

    public void setList(List<ML_Entry> list) {
        this.list = list;
    }
}
