package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.09.04.
 */
public class MS_Entry {
    @SerializedName("movie_id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("input_date")
    private String time;

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
