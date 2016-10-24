package com.treasure_ct.android_xt.studyfragment.loadingways.asynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by treasure on 2016.08.31.
 */
public class AsyncTask_SimpleUse extends AppCompatActivity {
    private TextView text1,text2,text3,text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_simpleuse);
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
        text1 = (TextView) findViewById(R.id.async_text1);
        text2 = (TextView) findViewById(R.id.async_text2);
        text3 = (TextView) findViewById(R.id.async_text3);
        text4 = (TextView) findViewById(R.id.async_text4);
        new AsyncTask_SimpleTask().executeOnExecutor(executor,text1);
        new AsyncTask_SimpleTask().executeOnExecutor(executor,text2);
        new AsyncTask_SimpleTask().executeOnExecutor(executor,text3);
        new AsyncTask_SimpleTask().executeOnExecutor(executor,text4);
    }
}
