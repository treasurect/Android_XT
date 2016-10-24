package com.treasure_ct.android_xt.studyfragment.components.service.musicdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by treasure on 2016.09.21.
 */
public class MusicService extends Service{
    private static final String TAG = "MusicService";
    private MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        //使用音乐资源，来创建MediaPlayer，可直接播放
//        player = MediaPlayer.create(this, R.raw.nobody);
        player = new MediaPlayer();
        try {
            player.setDataSource(this, Uri.parse("http://10.0.153.160:8080/nobody.mp3"));
            //设置准备歌曲资源，开启线程，加载资源
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务器的时候，调用startService（）的时候，都会自动调用这个方法
     *
     * 可以认为这个方法就是来接受其他组件传递的参数的
     *
     * @param intent  --->>>  就是startService（)中的参数
     * @param flags
     * @param startId
     * @return   返回值能控制服务是否复活，在程序意外退出的时候
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: -");
        if (intent != null) {
            String action = intent.getStringExtra("action");
            if ("play".equals(action)) {
                player.start();
            } else if ("pause".equals(action)) {
                player.pause();
            }
        }

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        //释放各种资源
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
        player = null;
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
