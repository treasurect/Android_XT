package com.treasure_ct.android_xt.studyfragment.corecontrols.listview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.09.02.
 */
public class Base_Entry {
    @SerializedName("title")
    private String text;
    @SerializedName("img")
    private String imgUrl;
    @SerializedName("id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Base_Entry(String text, String imgUrl) {
        this.text = text;
        this.imgUrl = imgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return "http://tnfs.tngou.net/img"+imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
