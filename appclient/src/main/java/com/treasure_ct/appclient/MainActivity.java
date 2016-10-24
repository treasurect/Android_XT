package com.treasure_ct.appclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定远程服务
        Intent intent = new Intent();
        //绑定远程服务的类信息，必须写
        intent.setClassName(
                "com.treasure_ct.android_xt",
                "com.treasure_ct.android_xt.components.service.remoteservicedemo.CalcService");
        bindService(intent,this,BIND_AUTO_CREATE);
    }

    public void btnStartService(View view) {
        Intent intent = new Intent();
        //设置类名的形式，可以调用其他程序的服务
        //参数1：String 远程程序的 applicationId
        intent.setClassName(
                "com.treasure_ct.android_xt",
                "com.treasure_ct.android_xt.components.service.remoteservicedemo.CalcService");
        startService(intent);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
