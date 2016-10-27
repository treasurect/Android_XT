package com.treasure_ct.android_xt.studyfragment.studyadvance.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;

public class RXJavaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        Button simpleUse = (Button) findViewById(R.id.rxJava_simpleUse);
        Button longTime = (Button) findViewById(R.id.rxJava_LongTime);
        Button douBan = (Button) findViewById(R.id.rxJavaDouban);

        simpleUse.setOnClickListener(this);
        longTime.setOnClickListener(this);
        douBan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rxJava_simpleUse:
                startActivity(new Intent(this,RXJavaSimpleUseActivity.class));
                break;
            case R.id.rxJava_LongTime:
                startActivity(new Intent(this,LongTimeActivity.class));
                break;
            case R.id.rxJavaDouban:
                startActivity(new Intent(this,DoubanActivity.class));
                break;
        }
    }
}
