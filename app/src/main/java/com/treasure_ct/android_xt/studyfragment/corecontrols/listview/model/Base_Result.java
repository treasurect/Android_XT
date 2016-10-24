package com.treasure_ct.android_xt.studyfragment.corecontrols.listview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by treasure on 2016.09.03.
 */
public class Base_Result {
    @SerializedName("status")
    private boolean status;
    @SerializedName("total")
    private int total;
    @SerializedName("tngou")
    private List<Base_Entry>list;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Base_Entry> getList() {
        return list;
    }

    public void setList(List<Base_Entry> list) {
        this.list = list;
    }
}
