package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.FourthFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.CommonFragmentPagerAdapter;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.SecondFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.ThirdFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.FirstFragment;

import java.util.ArrayList;

/**
 * Android 5.0 后 TabLayout可以与viewpager直接联动
 */
public class TabLayout2Activity extends AppCompatActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_viewpager_tablayout);
        //1.TabLayout可以使用代码添加多个Tab
        //2.可以与ViewPager直接联动
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.tab_viewpager);

        //实现adapter 的 滚动效果
        ArrayList<BaseFragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThirdFragment());
        list.add(new FourthFragment());
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        // 3. setup
        // setupWithViewPager 要求 ViewPager的 Adapter必须要实现
        //    getPagerTitle(int position)
        tabLayout.setupWithViewPager(pager);
    }
}
