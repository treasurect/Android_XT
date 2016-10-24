package com.treasure_ct.android_xt.studyfragment.components.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Ordered0Recevide extends BroadcastReceiver {
    private static final String TAG = "Ordered0Recevide";
    public Ordered0Recevide() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "onReceive: ");
        abortBroadcast();//拦截

        //设置处理的结果
        Bundle bundle = new Bundle();
        bundle.putString("username","user");
        setResult(666,"treasure",bundle);
    }
}
