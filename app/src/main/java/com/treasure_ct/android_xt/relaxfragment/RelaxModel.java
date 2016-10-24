package com.treasure_ct.android_xt.relaxfragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by treasure on 2016.10.21.
 */

public class RelaxModel {
    private int width;
    private int height;
    private List<String> list;

    public static RelaxModel createFromJson(JSONObject json) {
        RelaxModel ret = null;
        if (json != null) {
            try {
                ret = new RelaxModel();
                ret.width = json.getInt("width");
                ret.height = json.getInt("height");
                JSONArray array = json.getJSONArray("url_list");
                ret.list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    ret.list.add(object.getString("url"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    public String getFirstUrl(){
        String ret = null;
        if (list != null && !list.isEmpty()){
            ret = list.get(0);
        }
        return ret;
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
