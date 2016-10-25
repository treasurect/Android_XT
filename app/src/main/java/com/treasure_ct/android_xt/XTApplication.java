package com.treasure_ct.android_xt;

import android.app.Application;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import org.xutils.*;
import org.xutils.BuildConfig;

/**
 * Created by treasure on 2016.10.21.
 */

public class XTApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //环信初始化
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);//好友添加不需要验证
        EMClient emClient = EMClient.getInstance();
        emClient.init(this,options);
        if (BuildConfig.DEBUG){
            emClient.setDebugMode(true);
        }

    }
}
