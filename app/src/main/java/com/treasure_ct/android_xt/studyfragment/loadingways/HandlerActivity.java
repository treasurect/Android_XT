package com.treasure_ct.android_xt.studyfragment.loadingways;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.HandlerChatActivity;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.Handler_NetWork_Image;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.Handler_NetWork_Pager;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.Handler_LoopNum;

/**
 * Created by treasure on 2016.08.30.
 */
public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        btn1 = (Button) findViewById(R.id.loop_num);
        btn2 = (Button) findViewById(R.id.net_pager);
        btn3 = (Button) findViewById(R.id.net_image);
        btn4 = (Button) findViewById(R.id.chat_robot);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loop_num:
                Intent intent = new Intent(this, Handler_LoopNum.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(this,intent,compat.toBundle());
                break;
            case R.id.net_pager:
                Intent intent1 = new Intent(this, Handler_NetWork_Pager.class);
                ActivityOptionsCompat compat1 = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(this,intent1,compat1.toBundle());
                break;
            case R.id.net_image:
                Intent intent2 = new Intent(this, Handler_NetWork_Image.class);
                ActivityOptionsCompat compat2 = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(this,intent2,compat2.toBundle());
                break;
            case R.id.chat_robot:
                Intent intent3 = new Intent(this, HandlerChatActivity.class);
                ActivityOptionsCompat compat3 = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(this,intent3,compat3.toBundle());
                break;
        }
    }
}
