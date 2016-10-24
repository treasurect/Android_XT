package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.SecondFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.CommonFragmentPagerAdapter;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.FirstFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.FourthFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.ThirdFragment;

import java.util.ArrayList;

/**
 * Android 5.0 后 TabLayout可以与viewpager直接联动
 */
public class TabLayoutActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_viewpager_tablayout);
        //1.TabLayout可以使用代码添加多个Tab
        //2.可以与ViewPager直接联动
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.tab_viewpager);
        //3.在ViewPager对象初始化之后，TabLayout可以设置一个TabListener
        //因为TabLayout的第一个Tab创建的时候，会自动调用Listener
        tabLayout.addOnTabSelectedListener(this);
        //创建Tab对象
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText("首页");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("新闻");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("娱乐");
        tabLayout.addTab(tab);
        tab = tabLayout.newTab();
        tab.setText("文化");
        tabLayout.addTab(tab);

        //实现adapter 的 滚动效果
        ArrayList<BaseFragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThirdFragment());
        list.add(new FourthFragment());
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);

        //TabLayout内部实现了一个OnPageChangeListener,能够实现跟随
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //当tab从没有选中到选中的状态调用此方法
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //当前Tab已经是被选中的情况下，再次点击这个Tab，会回调这个方法
    }
}
