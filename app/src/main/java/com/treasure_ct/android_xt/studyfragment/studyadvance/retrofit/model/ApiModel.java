package com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.10.27.
 */

public class ApiModel {
    @SerializedName("count")
    private int count;
    @SerializedName("fcount")
    private int fcount;
    @SerializedName("galleryclass")
    private int galleryclass;
    @SerializedName("id")
    private int id;
    @SerializedName("img")
    private String img;
    @SerializedName("rcount")
    private int rcount;
    @SerializedName("time")
    private String time;
    @SerializedName("title")
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
