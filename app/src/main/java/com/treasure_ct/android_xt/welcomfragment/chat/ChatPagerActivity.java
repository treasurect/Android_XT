package com.treasure_ct.android_xt.welcomfragment.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

public class ChatPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_pager);
        String name = getIntent().getStringExtra("name");
        TextView title = (TextView) findViewById(R.id.chat_pager_title);
        title.setText(name);
    }
}
