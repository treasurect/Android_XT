package com.treasure_ct.android_xt.studyfragment.components.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.treasure_ct.android_xt.R;

/**
 * android四大组件分别为activity、service、content provider、broadcast receiver。
 * activity的生命周期
 * android中不要出现静态属性，因为值很容易变换
 * 空进程（系统扫描到就挂），后台执行activity进程（资源不足)，后台service进程，前台运行activity进程，系统进程(手机重启)
 *
 */

/**
 * Created by treasure on 2016.08.29.
 */
public class                                                                                                                     ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_activity_image);
    }
}
