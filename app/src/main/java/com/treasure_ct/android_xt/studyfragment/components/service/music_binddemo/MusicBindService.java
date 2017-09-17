package com.treasure_ct.android_xt.studyfragment.components.service.music_binddemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.treasure_ct.android_xt.R;

public class MusicBindService extends Service {
    private MediaPlayer player;
    public MusicBindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.nobody);
    }

    /**
     * 绑定服务的实现流程：
     * 1。服务 onCreate ,onBind,onDestroy方法
     * 2.onBind 方法需要返回一个IBinder 对象
     * 如果Activity绑定，Activity就可以取到IBinder对象，可以直接调用对象的方法
     * @param
     * @return
     */

//    、、相同应用内部的不同组件绑定，可以使用内部类以及Binder对象返回
    public class MusicController extends Binder{
        public void play(){
            player.start();
        }
        public void pause(){
            player.pause();
        }
        public long getMusicDuration(){
            return player.getDuration();
        }
        public long getPositionTime(){
            return player.getCurrentPosition();
        }
    }
    //当绑定服务的时候自动回调这个方法，返回的对象可以直接操作Service内部的内容
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MusicController();
    }
    //任意一次unbindService()方法，都会触发这个方法
    //用于释放一些绑定时使用的资源
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        if (player.isPlaying()){
            player.stop();
        }
        player.release();
        player = null;
        super.onDestroy();
    }
}



