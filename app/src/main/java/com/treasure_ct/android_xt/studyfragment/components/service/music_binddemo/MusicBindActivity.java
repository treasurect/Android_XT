package com.treasure_ct.android_xt.studyfragment.components.service.music_binddemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

public class MusicBindActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection ,Runnable{
    private MusicBindService.MusicController mController;
    private ProgressBar mProgressBar;
    private boolean isRunning;
    private TextSwitcher mSwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_service_music_bind);
        Button play = (Button) findViewById(R.id.btn_music_play);
        Button pause = (Button) findViewById(R.id.btn_music_pause);
        mProgressBar = (ProgressBar) findViewById(R.id.music_progressbar);
        mSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        mSwitcher.addView(new TextView(this),0,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mSwitcher.addView(new TextView(this),1,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        new Thread(this).start();
        //绑定服务，使用Context绑定，那个界面需要昂定，就用那和Activity
        Intent intent = new Intent(this,MusicBindService.class);
        startService(intent);
        //参数1：Intent 需要绑定哪一个Service
        //参数2.是一个回调接口，可以接受到Service 连接成功和断开连接的回调，成功就可以获取到对象
        //绑定服务  参数2  就是 服务和指定的对象绑定在一起
        bindService(intent,this,BIND_AUTO_CREATE);

    }

    @Override
    protected void onStart() {
        isRunning = true;
        super.onStart();
    }

    @Override
    protected void onStop() {
        isRunning = false;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //解除绑定
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_music_play:
                if (mController != null) {
                    mController.play();
                }
                break;
            case R.id.btn_music_pause:
                if (mController != null) {
                    mController.pause();
                }
                break;
        }
    }

    @Override
    //-----------------------------------------
    //服务绑定与解除绑定的回调
    /**
     * 当服务与当前绑定对象，绑定成功，服务的onBind方法，调用并且返回之后，调用这个方法
     */
    public void onServiceConnected(ComponentName name, IBinder service) {
            mController = (MusicBindService.MusicController) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    public void run() {
        isRunning = true;
        try {
        while (isRunning) {
            if (mController != null) {
                final long duration = mController.getMusicDuration();
                final long position = mController.getPositionTime();
                mProgressBar.setMax((int) duration);
                mProgressBar.setProgress((int) position);
                mSwitcher.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwitcher.setText(position +"/"+duration);
                    }
                });
            }

            Thread.sleep(300);
        }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
