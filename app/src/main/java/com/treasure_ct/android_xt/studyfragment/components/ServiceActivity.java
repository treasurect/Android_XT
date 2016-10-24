package com.treasure_ct.android_xt.studyfragment.components;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.components.service.music_binddemo.MusicBindActivity;
import com.treasure_ct.android_xt.studyfragment.components.service.downloadfile.DownloadFileActivity;
import com.treasure_ct.android_xt.studyfragment.components.service.musicdemo.ServiceMusicDemo;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_service);
        Button music = (Button) findViewById(R.id.music_demo);
        Button download = (Button) findViewById(R.id.download_demo);
        Button music_bind = (Button) findViewById(R.id.music_bind_demo);
        music.setOnClickListener(this);
        download.setOnClickListener(this);
        music_bind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_demo:
                startActivity(new Intent(this, ServiceMusicDemo.class));
                break;
            case R.id.download_demo:
                startActivity(new Intent(this, DownloadFileActivity.class));
                break;
            case R.id.music_bind_demo:
                startActivity(new Intent(this, MusicBindActivity.class));
                break;
        }
    }
}
