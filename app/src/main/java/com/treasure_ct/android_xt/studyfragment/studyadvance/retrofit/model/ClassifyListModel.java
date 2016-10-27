package com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by treasure on 2016.10.27.
 */

public class ClassifyListModel {
    @SerializedName("status")
    private boolean status;
    @SerializedName("tngou")
    private List<ClassifyModel> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ClassifyModel> getTngou() {
        return tngou;
    }

    public void setTngou(List<ClassifyModel> tngou) {
        this.tngou = tngou;
    }
}
