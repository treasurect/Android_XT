package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.treasure_ct.android_xt.R;

public class SlideActivity extends AppCompatActivity implements View.OnTouchListener {
    private LinearLayout menu;
    private LinearLayout content;
    private float lastX,lastY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_eentdestribution_slide);
        menu = (LinearLayout) findViewById(R.id.menu_layout);
        content = (LinearLayout) findViewById(R.id.content_layout);
        //设置内容区的触摸事件
        content.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //实现触摸事件的处理
        boolean ret =false;
        int action = event.getAction();
        float eX = event.getRawX();
        float eY = event.getRawY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                //按下事件必须返回true才可继续接受move事件
                ret = true;
                lastX = eX;
                lastY = eY;
                break;
            case MotionEvent.ACTION_MOVE:
                float cX = eX - lastX;
                float cY = eY - lastY;
                //最多向右移动多少
                int width = menu.getWidth();
                //0
                float selfX = ViewCompat.getX(content);
                selfX += cX;
                if (selfX < 0){
                    selfX = 0;
                }else  if (selfX >width){
                    selfX = width;
                }
                ViewCompat.setX(content,selfX);
                lastX = eX;
                lastY = eY;
                break;
            case MotionEvent.ACTION_UP:

//                if ()
        }
        return ret;
    }
}
