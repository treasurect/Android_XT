package com.treasure_ct.android_xt.studyfragment.loadingways.handler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.pager.Handler_NetWork_Pager_Runnable;

/**
 * Created by treasure on 2016.08.30.
 */
public class Handler_NetWork_Pager extends AppCompatActivity implements Handler_NetWork_Pager_Runnable.MyCall {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_network_pager);
        textView = (TextView) findViewById(R.id.text_handler);
        Handler_NetWork_Pager_Runnable runable = new Handler_NetWork_Pager_Runnable("http://www.baidu.com/", this);
        new Thread(runable).start();
    }

    @Override
    public void onSuccess(String text) {
        textView.setText(text);
    }

    @Override
    public void onFail(Exception e, String message) {
        e.printStackTrace();
        Toast.makeText(Handler_NetWork_Pager.this, message, Toast.LENGTH_SHORT).show();
    }
}
