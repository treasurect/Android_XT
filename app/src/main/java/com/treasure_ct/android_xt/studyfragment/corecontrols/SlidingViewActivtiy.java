package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.SlidingMenu_Activity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.Sliding_Normal_Activity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.DrawerLayoutActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.SlidingDelListItemActivity;

public class SlidingViewActivtiy extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_sliding_main);
        Button normal = (Button) findViewById(R.id.sliding_normal);
        Button menu = (Button) findViewById(R.id.sliding_menu);
        Button drawerlayout = (Button) findViewById(R.id.sliding_drawerlayout);
        Button slidingdel = (Button) findViewById(R.id.slidingdel);
        normal.setOnClickListener(this);
        menu.setOnClickListener(this);
        drawerlayout.setOnClickListener(this);
        slidingdel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sliding_normal:
                startActivity(new Intent(this, Sliding_Normal_Activity.class));
                break;
            case R.id.sliding_menu:
                startActivity(new Intent(this, SlidingMenu_Activity.class));
                break;
            case R.id.sliding_drawerlayout:
                startActivity(new Intent(this, DrawerLayoutActivity.class));
                break;
            case R.id.slidingdel:
                startActivity(new Intent(this, SlidingDelListItemActivity.class));
                break;
        }
    }
}
