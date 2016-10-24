package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets.PictrueShowView;

public class FullCustomViewDemo extends AppCompatActivity implements Runnable {
    private PictrueShowView mView;
    private boolean isRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_customview_fullcustomviewdemo);
        mView = (PictrueShowView) findViewById(R.id.show_view);
        new Thread(this).start();
    }

    public void btnSwitch(View view) {
        mView.showNext();
    }

    @Override
    public void run() {
        isRunning = true;
        try {
            while (isRunning) {
                mView.showNext();

                Thread.sleep(80);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        isRunning = false;
        super.onDestroy();
    }
}
