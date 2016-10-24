package com.treasure_ct.android_xt.studyfragment.components.activity;

import android.app.Application;

/**
 * Created by treasure on 2016.08.30.
 */
public class Activity_Base_Application extends Application{
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setText("BaseApplication");
    }
}
