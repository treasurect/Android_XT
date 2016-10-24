package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.09.04.
 */
public class ML_Entry {
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("score")
    private int score;
    @SerializedName("releasetime")
    private String retime;
    @SerializedName("cover")
    private String cover;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRetime() {
        return retime;
    }

    public void setRetime(String retime) {
        this.retime = retime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
