package com.treasure_ct.android_xt.studyfragment.components.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Normal1Receiver extends BroadcastReceiver {
    private static final String TAG = "Normal1Receiver";
    public Normal1Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "onReceive: ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onReceive: "+"thread ----------------111");
    }
}
