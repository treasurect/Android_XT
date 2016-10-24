package com.treasure_ct.android_xt.studyfragment.simpledemo.newstoday_xt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.treasure_ct.android_xt.R;

public class NewsTodayActivity extends AppCompatActivity implements NewsListFragment.onNewsSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo_news_today);

        //使用FragmentManager来查找相应的Fragment，并且设置对应接口
        //实现Fragment与Fragment之间的交互
        FragmentManager manager = getSupportFragmentManager();
        NewsListFragment fragment = (NewsListFragment) manager.findFragmentById(R.id.newslist_list);
        if (fragment != null){

        }
    }

    @Override
    public void onNewsSelected(Bundle bundle) {
        Log.d("onNewsSelectedListener", "onNewsSelected: "+bundle);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_detail);
        if (fragment != null) {
            Log.d("---------------", "onNewsSelected: "+"fragment != null");
            DetailFragment detailFragment = (DetailFragment) fragment;

        }else {
            Log.d("------------------", "onNewsSelected: "+"fragment == null");
        }
    }
}
