package com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.adapter.MyCyclerViewGridAdapter;
import com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.divider.DividerItemDecorationGrid;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewGridVerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_recyclerview_grid_ver);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_girdver);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("这是一个item"+i);
        }
        MyCyclerViewGridAdapter adapter = new MyCyclerViewGridAdapter(this, list);
        //表格
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecorationGrid(this));
        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置增加或删除 item的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
