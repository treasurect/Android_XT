package com.treasure_ct.android_xt.studyfragment.components.broadcastreceiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.treasure_ct.android_xt.R;

public class BootReciver extends BroadcastReceiver {
    private static final String TAG = "BootReciver";
    public BootReciver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.\
        Log.d(TAG, "onRecei 手机启动了");
        NotificationCompat.Builder builder =new NotificationCompat.Builder(context);
        Notification build = builder.setContentText("手机启动了").setContentTitle("启动").setSmallIcon(R.mipmap.icon_main3).build();
        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        compat.notify(666,build);
    }
}
