package com.treasure_ct.android_xt.studyfragment.loadingways;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.asynctask.AsyncTask_DownLoad_Image;
import com.treasure_ct.android_xt.studyfragment.loadingways.asynctask.AsyncTask_NetWork;
import com.treasure_ct.android_xt.studyfragment.loadingways.asynctask.AsyncTask_SimpleUse;

/**
 * Created by treasure on 2016.08.31.
 */
public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {
    private Button text1,text2,text3,text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        text1 = (Button) findViewById(R.id.async_simpleuse);
        text2 = (Button) findViewById(R.id.async_network);
        text3 = (Button) findViewById(R.id.async_image);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.async_simpleuse:
                Intent intent = new Intent(this, AsyncTask_SimpleUse.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                startActivity(intent,compat.toBundle());
                break;
            case R.id.async_network:
                Intent intent1 = new Intent(this,AsyncTask_NetWork.class);
                ActivityOptionsCompat compat1 = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                startActivity(intent1,compat1.toBundle());
                break;
            case R.id.async_image:
                Intent intent2 = new Intent(this,AsyncTask_DownLoad_Image.class);
                ActivityOptionsCompat compat2 = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                startActivity(intent2,compat2.toBundle());
                break;
        }
    }
}
