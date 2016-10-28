package com.treasure_ct.android_xt.studyfragment.studyadvance.memoryoptimization;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.HashMap;

public class MemorySecondActivity extends AppCompatActivity {
    private static final String TAG = "MemorySecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyadvance_memory_second);
        TextView textView = (TextView) findViewById(R.id.getMemory);
        ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        //获取Android设备  限定的  一个应用程序 占用的内存
        int memoryClass = manager.getMemoryClass();

        //获取运行期间  内存的使用情况
        Runtime runtime = Runtime.getRuntime();
        //当前程序在当前时间，整个分配的内存空间，这个控件可以增加，由虚拟机自己处理
        long totalMemory = runtime.totalMemory();
        //当前程序在当前时间  虚拟机分配的内存中，可用的内存空间尺寸
        long freeMemory = runtime.freeMemory();
        textView.setText("限定内存：  " + memoryClass +
                        "\ntotalMemory : " + totalMemory +
                         "\nfreeMemory : " + freeMemory);
        LruCache<String, Bitmap> cache = new LruCache<>(memoryClass / 8);
    }

    public void btnJumppre(View view) {
        startActivity(new Intent(this,MemoryOptimizationActivity.class));
    }

    public void btnSparseArray(View view) {
        Runtime runtime = Runtime.getRuntime();
        HashMap<Integer, String> map = new HashMap<>();
//        SparseArrayCompat<String> map = new SparseArrayCompat<>();
        long preTime = System.currentTimeMillis();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        for (int i = 0; i < 1000; i++) {
            map.put(i,"index:" + i);
        }
        long totalMemory1 = runtime.totalMemory();
        long freeMemory1 = runtime.freeMemory();
        long nowTime = System.currentTimeMillis();
        long preUseMemory = totalMemory - freeMemory;
        long nowUseMemory = totalMemory1 - freeMemory1;
        long useMemory = nowUseMemory - preUseMemory;
        long useTime = nowTime - preTime;
        Log.d(TAG, " 内存使用" + useMemory + " 所用时间" + useTime);
    }
}
