package com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.10.27.
 */

public class InfoModel {
    @SerializedName("name")
    private String name;
    @SerializedName("version")
    private String version;
    @SerializedName("author")
    private String author;
    @SerializedName("updateTime")
    private String updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
