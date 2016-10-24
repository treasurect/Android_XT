package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.loading;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;

public class ViewPager_LoadingPage extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_view_pager_demo1);
        ViewPager pager =  (ViewPager)findViewById(R.id.loading_viewpager);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.mipmap.icon_suolong1);
        list.add(R.mipmap.loading);
        list.add(R.mipmap.icon_suolong2);
        list.add(R.mipmap.icon_suolong3);
        LoadingPageAdapter adapter = new LoadingPageAdapter(this,list);
        adapter.JumpNext(this);
        //可以设置预加载的页数
        //设置2   代表  当前页面的左右两侧两个页面会被保留（2 ~~~ length -3)
//        pager.setOffscreenPageLimit(2);
        pager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(ViewPager_LoadingPage.this, "进入成功", Toast.LENGTH_SHORT).show();
    }
}
