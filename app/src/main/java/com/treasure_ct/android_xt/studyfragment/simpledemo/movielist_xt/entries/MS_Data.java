package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.09.04.
 */
public class MS_Data {
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private MS_Entry data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MS_Entry getData() {
        return data;
    }

    public void setData(MS_Entry data) {
        this.data = data;
    }
}
