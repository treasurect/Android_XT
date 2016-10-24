package com.treasure_ct.android_xt.studyfragment.corecontrols.sliding;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.widgets.SlidingLinnearLayout;

import java.util.ArrayList;

public class Sliding_Normal_Activity extends AppCompatActivity implements View.OnTouchListener {
    private LinearLayout menu_show;
    private SlidingLinnearLayout content_show;
    private ListView content_listView;
    private ListView menu_listView;
    private float downX,downY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_sliding_normal);
        menu_show = (LinearLayout) findViewById(R.id.menu_show);
        content_show = (SlidingLinnearLayout) findViewById(R.id.content_show);
         content_listView = (ListView) findViewById(R.id.sliding_content_listView);
         menu_listView = (ListView) findViewById(R.id.sliding_menu_listView);
        if (content_listView != null){
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add("微信小程序都是浮云---"+i);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            content_listView.setAdapter(adapter);
            content_show.setOnTouchListener(this);
        }
        if (menu_listView != null){
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                list.add("JAVA才是王道---"+i);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            menu_listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean ret = false;
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        //sliding最多移动的距离
        int width = menu_show.getWidth();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = rawX;
                downY = rawY;
                //按下事件返回true，可以继续接受MOVE事件
                ret = true;
                break;
            case MotionEvent.ACTION_MOVE:
                //初始0
                float contentX = ViewCompat.getX(content_show);
                float moveX = rawX - downX;
                float moveY = rawY - downY;
                contentX += moveX;
                if (contentX < 0){
                    contentX = 0;
                }else if (contentX >width){
                    contentX = width;
                }
                ViewCompat.setX(content_show,contentX);
                downX = rawX;
                downY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                float content_moveX = ViewCompat.getX(content_show);
                if (content_moveX < width / 2){
                    content_moveX = 0;
                }else {
                    content_moveX = width;
                }
                ViewCompat.setX(content_show,content_moveX);
                break;
        }

        return ret;
    }
}
