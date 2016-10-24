package com.treasure_ct.android_xt.studyfragment.simpledemo.contact_backup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Backup_NetworkReceiver extends BroadcastReceiver {
    private static final String TAG = "Backup_NetworkReceiver";
    public Backup_NetworkReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: 111111111111111");
        // TODO: This method is called when the BroadcastReceiver is receiving
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null){
                switch (info.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        Intent intent1 = new Intent(context, BackupService.class);
                        context.startService(intent1);
                        break;
                }
            }
        }else{

        }
    }
}
