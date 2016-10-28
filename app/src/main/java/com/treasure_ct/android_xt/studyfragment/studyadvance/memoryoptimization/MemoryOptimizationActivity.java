package com.treasure_ct.android_xt.studyfragment.studyadvance.memoryoptimization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.treasure_ct.android_xt.R;

public class MemoryOptimizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyadvance_memory_optimization);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 释放内存空间 ，level代表释放的级别
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d("TrimMemory", "   主页面 onTrimMemory: ");
    }

    public void btnJumpSecond(View view) {
        startActivity(new Intent(this,MemorySecondActivity.class));
    }
}
