package com.treasure_ct.android_xt.studyfragment.corecontrols.fragment.weibo_demo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.treasure_ct.android_xt.R;

public class Weibo_Fang_Demo extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_weibo__fang__fragmentdemo);
        //1.主流的 底部切换页面的设计方式
        //底部RG，切换时替换FrameLayout中的Fragment
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_tab_bar);
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(this);
        }
        //2.每一个Fragment可以通过两种方式添加到Activity
        //第一种：xml使用<fragment>标签直接添加
        //第二种方法如下

        //如果想在fragment里面再加载fragment   则使用getChildFragmentManager()
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container,new Weibo_Home_Fragment());
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //进行代码切换Fragment
        switch (checkedId){
            case R.id.main_tab_item_home:
                //关于替换操作，如果之前有fragment，先删除在添加
                transaction.replace(R.id.fragment_container,new Weibo_Home_Fragment());
                break;
            case R.id.main_tab_item_dm:
                transaction.replace(R.id.fragment_container,new Weibo_DM_Fragment());
                break;
            case R.id.main_tab_item_search:
                transaction.replace(R.id.fragment_container,new Weibo_Search_Fragment());
                break;
            case R.id.main_tab_item_mine:
                transaction.replace(R.id.fragment_container,new Weibo_Mine_Fragment());
                break;
        }
        //一个事务只需要提交一次
        transaction.commit();
    }
}
