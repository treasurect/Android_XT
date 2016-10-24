package com.treasure_ct.android_xt.studyfragment.components.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Ordered1Recevider extends BroadcastReceiver {
    private static final String TAG = "Ordered1Recevider";
    public Ordered1Recevider() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "onReceive: ");
        //Intent 参数传递的是初始的广播信息
        int code = getResultCode();
        String data = getResultData();
        //传递的参数
        Bundle extras = getResultExtras(true);
        Log.d(TAG, "onReceive: "+code+"-----------"+data +"-------------"+extras);
    }
}
