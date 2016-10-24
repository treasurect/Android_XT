package com.treasure_ct.android_xt;

import android.app.Application;

import org.xutils.x;

/**
 * Created by treasure on 2016.10.21.
 */

public class XTApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
