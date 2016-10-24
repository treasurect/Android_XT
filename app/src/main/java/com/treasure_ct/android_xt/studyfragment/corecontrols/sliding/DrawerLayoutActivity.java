package com.treasure_ct.android_xt.studyfragment.corecontrols.sliding;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.fragment.weibo_demo.Weibo_DM_Fragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.fragment.weibo_demo.Weibo_Home_Fragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment.CommonFragmentAdapter;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.FirstFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.FourthFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.SecondFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.ThirdFragment;


public class DrawerLayoutActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager;
    private CommonFragmentAdapter adapter;
    private TabLayout tabLayout;
    private DrawerLayout drawLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sliding_drawerLayout_toolbar);
        viewPager = (ViewPager) findViewById(R.id.sliding_drawerLayout_viewPager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_drawerLayout_tabLayout);
        drawLayout = (DrawerLayout) findViewById(R.id.sliding_drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.sliding_drawerLayout_navigation);
        tabLayout.addOnTabSelectedListener(this);
        //创建Tab对象
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText("直播");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("推荐");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("番剧");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("分区");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("关注");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("发现");
        tabLayout.addTab(tab);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThirdFragment());
        list.add(new FourthFragment());
        list.add(new Weibo_DM_Fragment());
        list.add(new Weibo_Home_Fragment());
        adapter = new CommonFragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //打开 DrawerLayout
                drawLayout.openDrawer(GravityCompat.START, true);
                break;
        }
        return true;
    }

    /**
     * NavigationView  item的点击的监听
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_homepage:
                //TODO:需要切换当前页面为首页
                viewPager.setCurrentItem(0, false);
                drawLayout.closeDrawer(GravityCompat.START,true);
                break;
            case R.id.action_main_offline:
                //TODO：打开新的Activity
                Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
                drawLayout.closeDrawer(GravityCompat.START,true);
                break;
            case R.id.action_main_fav:
                //TODO：打开新的Activity
                drawLayout.closeDrawer(GravityCompat.START,true);
                break;
        }
        //TODO:  需要考虑哪些是新开页面，哪些是替换页面
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition(),false);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}