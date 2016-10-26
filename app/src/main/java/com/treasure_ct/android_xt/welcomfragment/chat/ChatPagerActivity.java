package com.treasure_ct.android_xt.welcomfragment.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

public class ChatPagerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_pager);
        String name = getIntent().getStringExtra("name");
        TextView title = (TextView) findViewById(R.id.chat_pager_title);
        EditText edit = (EditText) findViewById(R.id.chat_pager_edit);
        Button send = (Button) findViewById(R.id.chat_pager_send);
        ImageView voice = (ImageView) findViewById(R.id.chat_pager_voice);
        ImageView pictrue = (ImageView) findViewById(R.id.chat_pager_picture);
        ImageView camera = (ImageView) findViewById(R.id.chat_pager_camera);
        ImageView hongbao = (ImageView) findViewById(R.id.chat_pager_hongbao);
        ImageView icon = (ImageView) findViewById(R.id.chat_pager_icon);
        send.setOnClickListener(this);
        voice.setOnClickListener(this);
        pictrue.setOnClickListener(this);
        camera.setOnClickListener(this);
        hongbao.setOnClickListener(this);
        icon.setOnClickListener(this);
        title.setText(name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_pager_send:

                break;
            case R.id.chat_pager_voice:

                break;
            case R.id.chat_pager_picture:

                break;
            case R.id.chat_pager_camera:

                break;
            case R.id.chat_pager_hongbao:

                break;
            case R.id.chat_pager_icon:

                break;
        }
    }
}
