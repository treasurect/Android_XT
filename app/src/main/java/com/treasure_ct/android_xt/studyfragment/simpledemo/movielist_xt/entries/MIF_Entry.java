package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by treasure on 2016.09.04.
 */
public class MIF_Entry {
    @SerializedName("id")
    private long id;
    @SerializedName("detailcover")
    private String image;
    @SerializedName("title")
    private String title;
    @SerializedName("score")
    private int score;
    @SerializedName("releasetime")
    private String time;
    @SerializedName("officialstory")
    private String story;
    @SerializedName("info")
    private String actor;
    @SerializedName("photo")
    private List<String>photo;

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
