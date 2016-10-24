package com.treasure_ct.android_xt.studyfragment.components.service.downloadfile;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DownloadService extends Service implements Runnable{
    private List<String> list;
    private boolean isRunning;
    private Thread mThread;
    private static final String TAG = "DownloadService";
    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        list = new ArrayList<>();
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String url = intent.getStringExtra("url");
            if (url != null){
                list.add(url);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void run() {
        isRunning = true;
        try {
            while (isRunning){
                if (list != null && !list.isEmpty()){
                    String url = list.remove(0);
                    Log.d(TAG, "run: "+url);
                    //TODO:实现下载和保存
                    Thread.sleep(2000);
                }
                Thread.sleep(300);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        list.clear();
        list = null;
        super.onDestroy();
    }
}
