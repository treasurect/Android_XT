package com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by treasure on 2016.10.27.
 */

public class ApiListModel {
    @SerializedName("status")
    private boolean status;
    @SerializedName("total")
    private int total;
    @SerializedName("tngou")
    private List<ApiModel> tngou;

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

    public List<ApiModel> getTngou() {
        return tngou;
    }

    public void setTngou(List<ApiModel> tngou) {
        this.tngou = tngou;
    }
}
