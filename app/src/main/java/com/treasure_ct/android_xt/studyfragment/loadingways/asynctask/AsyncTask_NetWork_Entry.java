package com.treasure_ct.android_xt.studyfragment.loadingways.asynctask;

import com.google.gson.annotations.SerializedName;

/**
 * Created by treasure on 2016.08.31.
 */
public class AsyncTask_NetWork_Entry {
    @SerializedName("title")
    private String title;
    @SerializedName("message")
    private String message;
    @SerializedName("img")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
