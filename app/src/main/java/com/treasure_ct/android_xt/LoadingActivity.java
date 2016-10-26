package com.treasure_ct.android_xt;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.loading_anim);
                animation.setDuration(2000);
                animation.setInterpolator(new LinearInterpolator());
                animation.start();
                startActivity(new Intent(LoadingActivity.this,MainActivity.class));
                finish();
            }
        },2500);
    }
}
