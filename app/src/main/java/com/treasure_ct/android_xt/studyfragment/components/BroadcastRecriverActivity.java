package com.treasure_ct.android_xt.studyfragment.components;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.treasure_ct.android_xt.R;

public class BroadcastRecriverActivity extends AppCompatActivity {
    private static final String TAG = "BroadcastRecriverActivity";
    //动态注册
    //1.创建一个BroadcastReceiver对象
    //2.通过代码来注册要接受那些广播信息
    //3.不需要接受的时候，直接取消注册就好了
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @SuppressLint("LongLogTag")
        @Override
        public void onReceive(Context context, Intent intent) {
            /**
             * 1. onReceive默认执行在主线程，希望运行在子线程，使用Handler
             * 2. onReceive 方法在主线程执行时，最长时间为10s，超过被/killed
             * 3. 静态注册的广播接收者，在onReceive()方法完成，那么创建的对象也会被销毁
             * 4. 动态的广播接收者，可以在activity的onDestroy的时候被销毁对象，onPause只是取消监听，防止泄露
             */
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
                //BatteryManager内部定义了Intent包含的各种Extra
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                Log.d(TAG, "LEVEL "+level);
                Log.d(TAG, "PLUGGED "+plugged);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_broadcast_recriver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = this;
        IntentFilter filter = new IntentFilter();
        //电量变化广播。电量变化只能用动态注册来监听
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        context.registerReceiver(mReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void SendBroadcast(View view) {
        //发送无序广播
        Intent intent = new Intent("treasure012");
        intent.putExtra("treasure","username");
        Context context = this;
        context.sendBroadcast(intent);
    }

    public void SendBroadcastOrdered(View view) {
        Intent intent = new Intent("treasure_ct123");
//        sendOrderedBroadcast(intent,null);
    //参数2：是定义的权限，只有生命使用这个权限内容的广播接收者才可以接收到广播消息
        sendOrderedBroadcast(intent,"treasure_ct");
    }

    public void SendBroadcastLocal(View view) {
        //本地广播管理器
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent("treasure");
        //发送无序普通的广播
        manager.sendBroadcast(intent);
        //发送广播，并且等待所有的广播发送给广播接收者，当所有广播接收者执行完成，这个方法才结束
        manager.sendBroadcastSync(intent);
    }
}
