package com.treasure_ct.android_xt.studyfragment.components.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by treasure on 2016.09.20.
 */

/**
 * 广播接收者
 * 内部只有一个onReceiver的方法用来接收数据
 */
public class NetWorkChangedReceiver extends BroadcastReceiver{
    private static final String TAG = "NetWorkChangedReceiver";
    /**
     * 当广播信息收到的时候，毁掉这个方法，Intent内部包含了广播的完整信息
     * 生命周期依赖于onReceive方法    无onCreate onDestroy 方法
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: "+intent);
        //广播接收者的使用模式
        String action = intent.getAction();
        //因为广播接收者可以通知注册多个广播类型的接收
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)){
            //1.Intent中包含了广播的各种信息
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //代表有网络
                int type = info.getType();
                switch (type) {
                    case ConnectivityManager.TYPE_WIFI:
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        break;
                    case ConnectivityManager.TYPE_ETHERNET:
                        break;
                }
                int subtype = info.getSubtype();
                switch (subtype) {
                }
            }else {
                //代表无网络
                Log.d(TAG, "onReceive: 无网络");
            }
            //2.网络状态的检查，通常使用 ConnectivityManager直接获取
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //找到系统当前状态  正在连接的网络信息，返回为null表示没网，飞行模式
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null){
                //有网络
            }else {
                //无网络
            }
        }
    }
}
