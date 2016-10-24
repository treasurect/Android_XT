package com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.entries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by treasure on 2016.09.03.
 */
public class Netease_Result {
    @SerializedName("T1348647853363")
    private List<Netease_Entry> data;

    public List<Netease_Entry> getData() {
        return data;
    }

    public void setData(List<Netease_Entry> data) {
        this.data = data;
    }
}
