package com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.entries;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by treasure on 2016.09.03.
 */
public class Netease_Entry {
    @SerializedName("title")
    private String title;
    @SerializedName("source")
    private String source;
    @SerializedName("imgsrc")
    private String image;
    @SerializedName("lmodify")
    private String time;
    @SerializedName("imgextra")
    private List<Map<String,String>>list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }
}
