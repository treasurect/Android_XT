package com.treasure_ct.android_xt.studyfragment.simpledemo.file_explorer_xt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.treasure_ct.android_xt.R;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_fileexplorer_loading);
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        String s = preferences.getString("password", null);
        if (s == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoadingActivity.this, Register_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2500);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                    startActivity(intent);
        finish();
    }
}, 2500);
        }
    }
}
