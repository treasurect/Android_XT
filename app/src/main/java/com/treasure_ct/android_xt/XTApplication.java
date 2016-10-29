package com.treasure_ct.android_xt;

import android.support.multidex.MultiDexApplication;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import org.xutils.*;
import org.xutils.BuildConfig;

import cn.smssdk.SMSSDK;

/**
 * Created by treasure on 2016.10.21.
 */

public class XTApplication extends MultiDexApplication{
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
        SMSSDK.initSDK(this,"1847114ba1735","786941ca8790a16335b0ba1b824b8d64");
    }
}
