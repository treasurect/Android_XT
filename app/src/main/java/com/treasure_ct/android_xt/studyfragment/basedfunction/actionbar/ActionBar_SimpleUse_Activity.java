package com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class ActionBar_SimpleUse_Activity extends AppCompatActivity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedfunction_actionbar_simpleuse);

        //ActionBar的基本使用
        //1.获取ActionBar，注意可能为null
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //2.设置
            //2.1设置后退，当被点击，相当一一个菜单点击方法，id =android.R.id.home
            actionBar.setDisplayHomeAsUpEnabled(true);//设置最左边的menu是否为后退
//            actionBar.setHomeAsUpIndicator(R.mipmap.icon_main1);//设置后退的图标
            actionBar.setDisplayShowTitleEnabled(false);//去掉标题的文字
            //2.2 导航模式，Tab模式，用于VP 和Fragment
            //1,。设置模式
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            //2.先创建Tab，设置监听器，添加Tab
            ActionBar.Tab tab = actionBar.newTab();
            tab.setTabListener(this);
            tab.setText("首页");
            actionBar.addTab(tab);

            tab = actionBar.newTab();
            tab.setTabListener(this);
            tab.setText("详情");
            actionBar.addTab(tab);

            tab = actionBar.newTab();
            tab.setTabListener(this);
            tab.setText("更多");
            actionBar.addTab(tab);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_main_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_settings:
                Toast.makeText(ActionBar_SimpleUse_Activity.this, "设置biubiubiu", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //FragmentTransaction  参数不允许commit（）调用就出错
        int position = tab.getPosition();
        Toast.makeText(ActionBar_SimpleUse_Activity.this, String.valueOf(position)+": "+tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        //FragmentTransaction  参数不允许commit（）调用就出错
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //FragmentTransaction  参数不允许commit（）调用就出错
    }
}
