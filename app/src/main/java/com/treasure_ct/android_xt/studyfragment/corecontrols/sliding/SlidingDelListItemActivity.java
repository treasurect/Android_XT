package com.treasure_ct.android_xt.studyfragment.corecontrols.sliding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

public class SlidingDelListItemActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_listview_sliding_list_view);
        TextView SwipeMenu = (TextView) findViewById(R.id.SwipeMenu_listView);
    }
}
