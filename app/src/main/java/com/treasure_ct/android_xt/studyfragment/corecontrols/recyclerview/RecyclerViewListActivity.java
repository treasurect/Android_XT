package com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.adapter.MyCyclerViewListAdapter;
import com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.divider.DividerItemDecorationList;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewListActivity extends AppCompatActivity {
    private MyCyclerViewListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_recyclerview_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("这是一个item"+i);
        }
         adapter = new MyCyclerViewListAdapter(this, list);
        //列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置垂直布局，也是默认布局
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecorationList(this,LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(new MyCyclerViewListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(RecyclerViewListActivity.this,"点击了第： "+position+"个item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(RecyclerViewListActivity.this, "长摁了第："+position+"个item", Toast.LENGTH_SHORT).show();
            }
        });
        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置增加或删除 item的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_recyclerview,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.recycler_add:
                adapter.addData(3);
                break;
            case R.id.recycler_remove:
                adapter.removeData(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
