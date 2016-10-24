package com.treasure_ct.android_xt.studyfragment.simpledemo.keymanager_xt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.treasure_ct.android_xt.R;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_keymanager_loading);
        if (getSharedPreferences("keymg_password",MODE_PRIVATE).getString("password",null) == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LoadingActivity.this,RegisterActivity.class));
                    finish();
                }
            },3000);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LoadingActivity.this,LoginActivity.class));
                    finish();
                }
            },3000);
        }

    }
}
