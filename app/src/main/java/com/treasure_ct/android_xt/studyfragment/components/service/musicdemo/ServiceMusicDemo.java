package com.treasure_ct.android_xt.studyfragment.components.service.musicdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;

public class ServiceMusicDemo extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_service_music);
        Button play = (Button) findViewById(R.id.btn_play);
        Button pause = (Button) findViewById(R.id.btn_pause);
        Button stop = (Button) findViewById(R.id.btn_edit_service);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        //context.startService(XXX)来启动服务

        Intent intent = new Intent(this,MusicService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                Intent intent = new Intent(this, MusicService.class);
                intent.putExtra("action","play");
                startService(intent);
                break;
            case R.id.btn_pause:
                Intent intent2 = new Intent(this, MusicService.class);
                intent2.putExtra("action","pause");
                startService(intent2);
                break;
            case R.id.btn_edit_service:
                Intent intent3 = new Intent(this, MusicService.class);
                //停止服务
                stopService(intent3);
                break;
        }
    }
}
